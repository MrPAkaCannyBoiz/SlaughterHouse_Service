package org.example.slaughterhouse_service.service.grpc;

import com.example.slaughterhouseService.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationOneImplClient
{
    private final StationOneServiceGrpc.StationOneServiceBlockingStub stationOneStub;

    public StationOneImplClient()
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("Localhost", 5678)
                .usePlaintext()
                .build();
        this.stationOneStub = StationOneServiceGrpc.newBlockingStub(channel);
    }

    public Animal registerAnimal(int animalTypeId, double weight, String origin) //parameter is message of type RegisterAnimalRequest in proto file
    {
        RegisterAnimalRequest request = RegisterAnimalRequest.newBuilder()
                .setAnimalTypeId(animalTypeId)
                .setWeight(weight)
                .setOrigin(origin)
                .build();
        RegisterAnimalResponse response = stationOneStub.registerAnimal(request);
        return response.getAnimal();
    }

    public Animal getAnimal(int animalTypeId)
    {
        GetAnimalByIdRequest request = GetAnimalByIdRequest.newBuilder()
                .setAnimalId(animalTypeId)
                .build();
        GetAnimalByIdResponse response = this.stationOneStub.getAnimalById(request);
        return response.getAnimal();
    }

    public List<Animal> getAllAnimals()
    {
        GetAllAnimalsRequest request = GetAllAnimalsRequest.newBuilder()
                .build();
        GetAllAnimalsResponse response = this.stationOneStub.getAllAnimals(request);
        return response.getAnimalsList();
    }
}
