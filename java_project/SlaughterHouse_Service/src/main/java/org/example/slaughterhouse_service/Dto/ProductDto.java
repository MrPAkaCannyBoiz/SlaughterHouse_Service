package org.example.slaughterhouse_service.Dto;

import org.example.slaughterhouse_service.entities.ProductEntity;

public class ProductDto
{
  private int id;
  private double totalWeight;
  private int trayId1;
  private int trayId2;

  public ProductDto(int id, double totalWeight, int trayId1, int trayId2)
  {
    this.id = id;
    this.totalWeight = totalWeight;
    this.trayId1 = trayId1;
    this.trayId2 = trayId2;
  }
  public int getId()
  {
    return id;
  }
  public void setId(int id)
  {
    this.id = id;
  }
  public double getTotalWeight()
  {
    return totalWeight;
  }
  public void setTotalWeight(double totalWeight)
  {
    this.totalWeight = totalWeight;
  }
  public int getTrayId1()
  {
    return trayId1;
  }
  public void setTrayId1(int trayId1)
  {
    this.trayId1 = trayId1;
  }

  public int getTrayId2()
  {
    return trayId2;
  }

  public void setTrayId2(int trayId2)
  {
    this.trayId2 = trayId2;
  }

  public static ProductDto fromEntity(ProductEntity productEntity)
  {
    int t1 = (productEntity.getTray() != null) ? productEntity.getTray().getId() : null;
    int t2 = (productEntity.getTray2() != null) ? productEntity.getTray2().getId() : null;
    return new ProductDto(
        productEntity.getId(),
        productEntity.getTotalWeight(),
        t1,
        t2
    );
  }
}
