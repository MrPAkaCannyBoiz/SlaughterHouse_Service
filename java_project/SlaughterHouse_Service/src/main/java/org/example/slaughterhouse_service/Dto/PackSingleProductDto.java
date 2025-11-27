package org.example.slaughterhouse_service.Dto;

public class PackSingleProductDto
{
  private int trayId;
  private double targetWeight;

  public PackSingleProductDto(int trayId, double targetWeight)
  {
    this.trayId = trayId;
    this.targetWeight = targetWeight;
  }
public int getTrayId()
{
  return trayId;
}

  public void setTrayId(int trayId)
  {
    this.trayId = trayId;
  }

  public double getTargetWeight()
  {
    return targetWeight;
  }

  public void setTargetWeight(double targetWeight)
  {
    this.targetWeight = targetWeight;
  }

}
