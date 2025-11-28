package org.example.slaughterhouse_service.service.grpc;

import com.example.slaughterhouseService.CutRequest;
import com.example.slaughterhouseService.CutResponse;
import com.example.slaughterhouseService.Part;
import com.example.slaughterhouseService.StationTwoServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationTwoImplClient
{
    private final StationTwoServiceGrpc.StationTwoServiceBlockingStub stationTwoStub;

    public StationTwoImplClient()
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5678)
                .usePlaintext()
                .build();

        this.stationTwoStub = StationTwoServiceGrpc.newBlockingStub(channel);
    }

    public List<Part> cut(int animalId)
    {
        CutRequest request = CutRequest.newBuilder()
                .setAnimalId(animalId)
                .build();

        CutResponse response = stationTwoStub.cut(request);
        return response.getPartsList();
    }
}
