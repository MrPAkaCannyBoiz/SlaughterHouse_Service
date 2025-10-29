package org.example.slaughterhouse_service.controller;

import org.example.slaughterhouse_service.entities.AnimalEntity;
import org.example.slaughterhouse_service.entities.AnimalEntityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.slaughterhouse_service.service.repositories.AnimalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animal")
public class AnimalRegistrationController
{
    private AnimalRepository animalRepository;

    @Autowired
    public AnimalRegistrationController(AnimalRepository animalRepository)
    {
        this.animalRepository = animalRepository;
    }

    @PostMapping("/register")
    public void registerAnimal(AnimalEntity animal)
    {
        animalRepository.save(animal);
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
