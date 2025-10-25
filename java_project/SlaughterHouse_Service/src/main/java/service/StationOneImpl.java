package service;

import com.google.protobuf.Timestamp;
import entities.AnimalEntity;
import entities.AnimalTypeEntity;
import generated.Animal;
import generated.RegisterAnimalRequest;
import generated.RegisterAnimalResponse;
import generated.StationOneServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AnimalRepository;
import repositories.AnimalTypeRepository;


import java.time.Instant;
import java.time.LocalDate;

@Service
public class StationOneImpl extends StationOneServiceGrpc.StationOneServiceImplBase
{
    private final AnimalRepository animalRepository;
    private final AnimalTypeRepository animalTypeRepository;

    @Autowired
    public StationOneImpl(AnimalRepository animalRepository, AnimalTypeRepository animalTypeRepository)
    {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
    }


    public void registerAnimal(RegisterAnimalRequest request,
                               StreamObserver<RegisterAnimalResponse> responseObserver)
    {
        // make entity for DB
        AnimalTypeEntity animalTypeEntity = animalTypeRepository.findById(request.getAnimalTypeId()).orElse(null);
        AnimalEntity animalEntity = new AnimalEntity(animalTypeEntity, request.getWeight(), request.getOrigin());
        AnimalEntity saved = animalRepository.save(animalEntity);

        // make dto for response
        System.out.println("Registering animal: " + request);

        // convert LocalDate to Timestamp for arrivalDate
        LocalDate arrivalDate = saved.getArrivalDate();
        Instant arrivalInstant = arrivalDate.atStartOfDay().toInstant(java.time.ZoneOffset.UTC);
        Timestamp arrivalTimestamp = Timestamp.newBuilder()
                .setSeconds(arrivalInstant.getEpochSecond())
                .setNanos(arrivalInstant.getNano())
                .build();

        Animal animalDto = Animal.newBuilder()
                .setId(saved.getId())
                .setWeight(saved.getWeight())
                .setType(saved.getAnimalType().getName())
                .setArrivalTime(arrivalTimestamp)
                .setOrigin(saved.getOrigin())
                .build();

        RegisterAnimalResponse response = RegisterAnimalResponse.newBuilder()
                .setAnimal(animalDto).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}

