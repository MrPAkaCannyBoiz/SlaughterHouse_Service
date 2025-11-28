package org.example.slaughterhouse_service.Dto;

public class PartDto
{
    private long id;
    private long animalId;
    private double weight;
    private long trayId;
    private int partTypeId;

    public PartDto(long id, long animalId, double weight, long trayId, int partTypeId)
    {
        this.id = id;
        this.animalId = animalId;
        this.weight = weight;
        this.trayId = trayId;
        this.partTypeId = partTypeId;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getAnimalId()
    {
        return animalId;
    }

    public void setAnimalId(long animalId)
    {
        this.animalId = animalId;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public long getTrayId()
    {
        return trayId;
    }

    public void setTrayId(long trayId)
    {
        this.trayId = trayId;
    }

    public int getPartTypeId()
    {
        return partTypeId;
    }

    public void setPartTypeId(int partTypeId)
    {
        this.partTypeId = partTypeId;
    }
}
