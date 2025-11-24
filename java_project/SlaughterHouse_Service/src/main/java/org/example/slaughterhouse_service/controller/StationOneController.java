package org.example.slaughterhouse_service.controller;

import com.example.slaughterhouseService.Animal;
import org.example.slaughterhouse_service.Dto.AnimalRegisterDto;
import org.example.slaughterhouse_service.entities.AnimalEntity;
import org.example.slaughterhouse_service.Dto.AnimalEntityDto;
import org.example.slaughterhouse_service.service.grpc.StationOneImplClient;
import org.example.slaughterhouse_service.service.repositories.AnimalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.example.slaughterhouse_service.service.repositories.AnimalRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animal")
public class StationOneController
{
    private AnimalRepository animalRepository; // have to be delete if you want REST as gRPC gateway

    private AnimalTypeRepository animalTypeRepository;
    private StationOneImplClient stationOneImplClient;

    @Autowired
    public StationOneController(AnimalRepository animalRepository, AnimalTypeRepository animalTypeRepository, StationOneImplClient stationOneImplClient)
    {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.stationOneImplClient = stationOneImplClient;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnimalEntityDto registerAnimal(@RequestBody AnimalRegisterDto dto)
    {
        Animal animalGrpc = stationOneImplClient
                .registerAnimal(dto.getAnimalTypeId(), dto.getWeight(), dto.getOrigin());
        System.out.println("Received registration request: " + dto);
        return toDto(animalGrpc);
    }

    @GetMapping("/test")
    public String testEndpoint()
    {
        return "Animal Registration Controller is working!";
    }


    @GetMapping
    public List<AnimalEntityDto> getAllAnimals()
    {
        List<Animal> animalsGrpc = stationOneImplClient.getAllAnimals();
        List<AnimalEntityDto> dtoList = new ArrayList<>();
        for (Animal a : animalsGrpc)
        {
            dtoList.add(toDto(a));
        }
        return dtoList;
    }

    @GetMapping("/{id}")
    public AnimalEntityDto getAnimalById(@PathVariable Integer id)
    {
       Animal animalGrpc = stationOneImplClient.getAnimal(id);
       return toDto(animalGrpc);
    }

    @GetMapping("/typeId")
    public List<AnimalEntityDto> getAnimalsByTypeId(Integer typeId)
    {
        return animalRepository.findByTypeId(typeId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/origin")
    public List<AnimalEntityDto> getAnimalsByOrigin(String origin)
    {
        return animalRepository.findByOrigin(origin)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/arrivalDate")
    public List<AnimalEntityDto> getAnimalsByArrivalDate(LocalDate arrivalDate)
    {
        return animalRepository.findByArrivalDate(arrivalDate)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // convert to dto (because you don't want to expose the entity directly)
    private AnimalEntityDto toDto(AnimalEntity animalEntity)
    {
        AnimalEntityDto dto = new AnimalEntityDto(
                animalEntity.getId(),
                animalEntity.getAnimalType().getId(),
                animalEntity.getWeight(),
                animalEntity.getArrivalDate(),
                animalEntity.getOrigin(),
                animalEntity.isAlive()
        );
        return dto;
    }

    private AnimalEntityDto toDto(Animal animalGrpc)
    {
        AnimalEntityDto dto = new AnimalEntityDto(
                animalGrpc.getId(),
                animalGrpc.getAnimalTypeId(),
                animalGrpc.getWeight(),
                LocalDate.now(), // arrivalDate is not available in gRPC response
                animalGrpc.getOrigin(),
                animalGrpc.getIsAlive()
        );
        return dto;
    }

}
