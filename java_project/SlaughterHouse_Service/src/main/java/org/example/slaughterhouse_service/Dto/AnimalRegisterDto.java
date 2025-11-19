package org.example.slaughterhouse_service.Dto;

public class AnimalRegisterDto
{
    private int animalTypeId;
    private double weight;
    private String origin;

    public AnimalRegisterDto(int animalTypeId, double weight, String origin)
    {
        this.animalTypeId = animalTypeId;
        this.weight = weight;
        this.origin = origin;
    }

    public int getAnimalTypeId()
    {
        return animalTypeId;
    }

    public void setAnimalTypeId(int animalTypeId)
    {
        this.animalTypeId = animalTypeId;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public String getOrigin()
    {
        return origin;
    }

    public void setOrigin(String origin)
    {
        this.origin = origin;
    }
}
