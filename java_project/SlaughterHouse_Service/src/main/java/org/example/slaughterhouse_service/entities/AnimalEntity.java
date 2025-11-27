package org.example.slaughterhouse_service.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "animal")
public class AnimalEntity
{
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column (name = "weight", nullable = false)
  private double weight;

    @ManyToOne @JoinColumn(name = "animal_type_id", nullable = false)
  private AnimalTypeEntity type;


    @Column (name = "is_alive", nullable = false)
    private boolean isAlive = true;


    @Column (name = "arrival_date" , nullable = false)
    private LocalDate arrivalDate = LocalDate.now();

    @Column (name = "origin" , nullable = false)
    private String origin = "Unknown";

    public AnimalEntity(AnimalTypeEntity animalTypeEntity, double weight) {
        this.type = animalTypeEntity;
        this.weight = weight;
    }

    public AnimalEntity(AnimalTypeEntity animalTypeEntity, double weight, String origin)
    {
        this.type = animalTypeEntity;
        this.weight = weight;
        this.origin = origin;
    }


  public AnimalEntity()
  {

  }


    public void setType(AnimalTypeEntity type)
    {
        this.type = type;
    }

    public AnimalTypeEntity getAnimalType()
    {
        return type;
    }

  public double getWeight()
  {
    return weight;
  }

  public void setWeight(int weight)
  {
    this.weight = weight;
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

    public String getOrigin()
    {
        return origin;
    }

    public void setOrigin(String origin)
    {
        this.origin = origin;
    }

}
