package org.example.slaughterhouse_service.service.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class StationThreeImpClient
{
  private final com.example.slaughterhouseService.StationThreeServiceGrpc.StationThreeServiceBlockingStub stationThreeStub;

  public StationThreeImpClient()

  {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("Localhost", 5678)
        .usePlaintext()
        .build();
    this.stationThreeStub = com.example.slaughterhouseService.StationThreeServiceGrpc.newBlockingStub(channel);
  }

  public com.example.slaughterhouseService.Product packSingglleProduct(int trayId)
  {
    com.example.slaughterhouseService.PackSingleProductRequest request = com.example.slaughterhouseService.PackSingleProductRequest.newBuilder()
        .setTrayId(trayId)
        .build();
    return stationThreeStub.packSingleProduct(request).getProduct();
  }
public com.example.slaughterhouseService.Product packHalfAnimal(int trayId1, int trayId2)
{
    com.example.slaughterhouseService.PackHalfAnimalRequest request = com.example.slaughterhouseService.PackHalfAnimalRequest.newBuilder()
        .setTrayId1(trayId1)
        .setTrayId2(trayId2)
        .build();
    return stationThreeStub.packHalfAnimal(request).getProduct();
}
}
