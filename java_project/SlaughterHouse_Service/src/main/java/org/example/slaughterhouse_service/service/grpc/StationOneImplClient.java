package org.example.slaughterhouse_service.service.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.slaughterhouse_service.generated.RegisterAnimalRequest;
import org.example.slaughterhouse_service.generated.RegisterAnimalResponse;
import org.example.slaughterhouse_service.generated.StationOneServiceGrpc;
import org.springframework.stereotype.Service;

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

    public String registerAnimal(int animalTypeId, double weight, String origin) //parameter is message of type RegisterAnimalRequest in proto file
    {
        RegisterAnimalRequest request = RegisterAnimalRequest.newBuilder()
                .setAnimalTypeId(animalTypeId)
                .setWeight(weight)
                .setOrigin(origin)
                .build();
        RegisterAnimalResponse response = this.stationOneStub.registerAnimal(request);
        return response.toString();
    }
}
