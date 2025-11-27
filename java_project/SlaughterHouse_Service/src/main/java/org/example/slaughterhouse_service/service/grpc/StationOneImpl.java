package org.example.slaughterhouse_service.service.grpc;

import com.google.protobuf.Timestamp;
import org.example.slaughterhouse_service.entities.AnimalEntity;
import org.example.slaughterhouse_service.entities.AnimalTypeEntity;
//import org.example.slaughterhouse_service.generated.Animal;
//import org.example.slaughterhouse_service.generated.RegisterAnimalRequest;
//import org.example.slaughterhouse_service.generated.RegisterAnimalResponse;
//import org.example.slaughterhouse_service.generated.StationOneServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.slaughterhouse_service.service.repositories.AnimalRepository;
import org.example.slaughterhouse_service.service.repositories.AnimalTypeRepository;


import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class StationOneImpl extends com.example.slaughterhouseService.StationOneServiceGrpc.StationOneServiceImplBase
{
    private final AnimalRepository animalRepository;
    private final AnimalTypeRepository animalTypeRepository;

    @Autowired
    public StationOneImpl(AnimalRepository animalRepository, AnimalTypeRepository animalTypeRepository)
    {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
    }


    public void registerAnimal(
        com.example.slaughterhouseService.RegisterAnimalRequest request,
                               StreamObserver<com.example.slaughterhouseService.RegisterAnimalResponse> responseObserver)
    {
        // make entity for DB
        AnimalTypeEntity animalTypeEntity = animalTypeRepository.findById(request.getAnimalTypeId()).orElse(null);
        AnimalEntity animalEntity = new AnimalEntity(animalTypeEntity, request.getWeight(), request.getOrigin());
        AnimalEntity saved = animalRepository.save(animalEntity);

        // make dto for response
        System.out.println("Registering animal: " + request);

        // convert LocalDate to Timestamp for arrivalDate
        LocalDate arrivalDate = saved.getArrivalDate();
        Timestamp arrivalTimestamp = convertLocalDateToTimestamp(arrivalDate);

        com.example.slaughterhouseService.Animal animalDto = com.example.slaughterhouseService.Animal.newBuilder()
                .setId(saved.getId())
                .setWeight(saved.getWeight())
                .setAnimalTypeId(saved.getAnimalType().getId())
                .setArrivalTime(arrivalTimestamp)
                .setOrigin(saved.getOrigin())
                .build();

        com.example.slaughterhouseService.RegisterAnimalResponse response = com.example.slaughterhouseService.RegisterAnimalResponse.newBuilder()
                .setAnimal(animalDto).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void getAnimalById(
        com.example.slaughterhouseService.GetAnimalByIdRequest request,
                              StreamObserver<com.example.slaughterhouseService.GetAnimalByIdResponse> responseObserver)
    {
        // find animal in DB
        var animal = animalRepository.findById(request.getAnimalId()).orElse(null);
        if (animal == null)
        {
            System.out.println("Animal with ID " + request.getAnimalId() + " not found.");
            // throw error to client with .onError()
            responseObserver.onError(new Throwable("Animal not found"));
        }
        else
        {
            // convert LocalDate to Timestamp for arrivalDate
            LocalDate arrivalDate = animal.getArrivalDate();
            Timestamp arrivalTimestamp = convertLocalDateToTimestamp(arrivalDate);

            com.example.slaughterhouseService.Animal animalDto = com.example.slaughterhouseService.Animal.newBuilder()
                    .setId(animal.getId())
                    .setWeight(animal.getWeight())
                    .setAnimalTypeId(animal.getAnimalType().getId())
                    .setArrivalTime(arrivalTimestamp)
                    .setOrigin(animal.getOrigin())
                    .build();

            com.example.slaughterhouseService.GetAnimalByIdResponse response = com.example.slaughterhouseService.GetAnimalByIdResponse.newBuilder()
                    .setAnimal(animalDto).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public void getAllAnimals(
        com.example.slaughterhouseService.GetAllAnimalsRequest request,
                              StreamObserver<com.example.slaughterhouseService.GetAllAnimalsResponse> responseObserver)
    {
        List<com.example.slaughterhouseService.Animal> animals = animalRepository.findAll()
                        .stream()
                                .map(animalEntity -> com.example.slaughterhouseService.Animal.newBuilder()
                                        .setId(animalEntity.getId())
                                        .setWeight(animalEntity.getWeight())
                                        .setAnimalTypeId(animalEntity.getAnimalType().getId())
                                        .setArrivalTime
                                                (convertLocalDateToTimestamp
                                                        (animalEntity.getArrivalDate()))
                                        .setOrigin(animalEntity.getOrigin())
                                        .build())
                                .toList();
        com.example.slaughterhouseService.GetAllAnimalsResponse response = com.example.slaughterhouseService.GetAllAnimalsResponse.newBuilder()
                .addAllAnimals(animals)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private Timestamp convertLocalDateToTimestamp(LocalDate date)
    {
        Instant instant = date.atStartOfDay().toInstant(java.time.ZoneOffset.UTC);
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(0)
                .build();
    }
}

