package org.example.slaughterhouse_service.Dto;

import java.time.LocalDate;

public class AnimalEntityDto
{

    private int id;
    private int typeId;
    private double weight;
    private String origin;
    private LocalDate arrivalDate;
    private boolean isAlive;

    public AnimalEntityDto(int id ,int typeId, double weight, LocalDate arrivalDate,
                           String origin, boolean isAlive)
    {
        this.typeId = typeId;
        this.weight = weight;
        this.origin = origin;
        setId(id);
        setAlive(isAlive);
        setArrivalDate(arrivalDate);
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setAlive(boolean alive)
    {
        isAlive = alive;
    }

    public LocalDate getArrivalDate()
    {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate)
    {
        this.arrivalDate = arrivalDate;
    }
}
