package org.example.slaughterhouse_service.Dto;

public class PackHalfAnimalDto
{
  private int trayId1;
  private int trayId2;

  public PackHalfAnimalDto(int trayId1, int trayId2)
  {
    this.trayId1 = trayId1;
    this.trayId2 = trayId2;
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
}


