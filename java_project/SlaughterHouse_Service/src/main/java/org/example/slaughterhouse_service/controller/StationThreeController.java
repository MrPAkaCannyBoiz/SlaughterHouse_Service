package org.example.slaughterhouse_service.controller;

import org.example.slaughterhouse_service.Dto.PackHalfAnimalDto;
import org.example.slaughterhouse_service.Dto.PackSingleProductDto;
import org.example.slaughterhouse_service.Dto.ProductDto;
import org.example.slaughterhouse_service.service.grpc.StationThreeImpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class StationThreeController
{

  private StationThreeImpClient stationThreeImpClient;

  @Autowired public StationThreeController(
      StationThreeImpClient stationThreeImpClient)
  {
    this.stationThreeImpClient = stationThreeImpClient;
  }

  @PostMapping("/single") public ProductDto packSingle(
      @RequestBody PackSingleProductDto packSingleProductDto)
  {
    com.example.slaughterhouseService.Product productGrpc = stationThreeImpClient.packSingglleProduct(
        packSingleProductDto.getTrayId());
    return toDto(productGrpc);
  }

  @PostMapping("/half_animal")
  public ProductDto packHalfAnimal(@RequestBody PackHalfAnimalDto packHalfAnimalDto)
  {
    com.example.slaughterhouseService.Product productGrpc = stationThreeImpClient.packHalfAnimal(
        packHalfAnimalDto.getTrayId1(), packHalfAnimalDto.getTrayId2());
    return toDto(productGrpc);
  }

  private ProductDto toDto(com.example.slaughterhouseService.Product p)
  {
    return new ProductDto(p.getId(),
        p.getTotalWeight(),
        p.getTrayId1(),
        p.getTrayId2()
    );
  }
}