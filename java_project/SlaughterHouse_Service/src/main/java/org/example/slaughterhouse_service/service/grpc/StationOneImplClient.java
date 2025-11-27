package org.example.slaughterhouse_service.service.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationOneImplClient
{
    private final com.example.slaughterhouseService.StationOneServiceGrpc.StationOneServiceBlockingStub stationOneStub;

    public StationOneImplClient()
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("Localhost", 5678)
                .usePlaintext()
                .build();
        this.stationOneStub = com.example.slaughterhouseService.StationOneServiceGrpc.newBlockingStub(channel);
    }

    public com.example.slaughterhouseService.Animal registerAnimal(int animalTypeId, double weight, String origin) //parameter is message of type RegisterAnimalRequest in proto file
    {
        com.example.slaughterhouseService.RegisterAnimalRequest request = com.example.slaughterhouseService.RegisterAnimalRequest.newBuilder()
                .setAnimalTypeId(animalTypeId)
                .setWeight(weight)
                .setOrigin(origin)
                .build();
        com.example.slaughterhouseService.RegisterAnimalResponse response = stationOneStub.registerAnimal(request);
        return response.getAnimal();
    }

    public com.example.slaughterhouseService.Animal getAnimal(int animalTypeId)
    {
        com.example.slaughterhouseService.GetAnimalByIdRequest request = com.example.slaughterhouseService.GetAnimalByIdRequest.newBuilder()
                .setAnimalId(animalTypeId)
                .build();
        com.example.slaughterhouseService.GetAnimalByIdResponse response = this.stationOneStub.getAnimalById(request);
        return response.getAnimal();
    }

    public List<com.example.slaughterhouseService.Animal> getAllAnimals()
    {
        com.example.slaughterhouseService.GetAllAnimalsRequest request = com.example.slaughterhouseService.GetAllAnimalsRequest.newBuilder()
                .build();
        com.example.slaughterhouseService.GetAllAnimalsResponse response = this.stationOneStub.getAllAnimals(request);
        return response.getAnimalsList();
    }
}
