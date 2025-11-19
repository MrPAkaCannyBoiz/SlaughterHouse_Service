package org.example.slaughterhouse_service.controller;

import org.example.slaughterhouse_service.Dto.AnimalRegisterDto;
import org.example.slaughterhouse_service.entities.AnimalEntity;
import org.example.slaughterhouse_service.Dto.AnimalEntityDto;
import org.example.slaughterhouse_service.entities.AnimalTypeEntity;
import org.example.slaughterhouse_service.service.grpc.StationOneImplClient;
import org.example.slaughterhouse_service.service.repositories.AnimalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.example.slaughterhouse_service.service.repositories.AnimalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animal")
public class AnimalRegistrationController
{
    private AnimalRepository animalRepository; // have to be delete if you want REST as gRPC gateway

    private AnimalTypeRepository animalTypeRepository;
    private StationOneImplClient stationOneImplClient;

    @Autowired
    public AnimalRegistrationController(AnimalRepository animalRepository, AnimalTypeRepository animalTypeRepository, StationOneImplClient stationOneImplClient)
    {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.stationOneImplClient = stationOneImplClient;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerAnimal(@RequestBody AnimalRegisterDto dto)
    {
        //stationOneImplClient.registerAnimal(dto.getAnimalTypeId(), dto.getWeight(), dto.getOrigin());
        // make entity for DB
        AnimalTypeEntity animalTypeEntity = animalTypeRepository.findById(dto.getAnimalTypeId()).orElse(null);
        AnimalEntity animalEntity = new AnimalEntity(animalTypeEntity, dto.getWeight(), dto.getOrigin());
        animalRepository.save(animalEntity);
        System.out.println("Received registration request: " + dto);
    }

    @GetMapping("/test")
    public String testEndpoint()
    {
        return "Animal Registration Controller is working!";
    }


    @GetMapping
    public List<AnimalEntityDto> getAllAnimals()
    {
        return animalRepository.findAll()
                .stream() // create a stream from the list, enabling functional operations.
                .map(this::toDto) // convert each AnimalEntity to AnimalEntityDto
                .collect(Collectors.toList()); // collect results into a new list
    }

    @GetMapping("/id")
    public AnimalEntityDto getAnimalById(Integer id)
    {
        return animalRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
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

}
