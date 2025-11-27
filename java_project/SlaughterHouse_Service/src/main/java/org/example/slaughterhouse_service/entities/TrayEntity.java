package org.example.slaughterhouse_service.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tray")
public class TrayEntity
{
    // rule to link with FK: @ManyToOne, and @JoinColumn. use association
  @Column(name = "maxweight", nullable = false)
  private double maxWeight;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;
  @OneToMany(mappedBy = "trayEntity", cascade = CascadeType.ALL) //which is the name of the attribute in Part class
  // this will not be in the database, it is just for us to easily access the parts in a tray
  // FK for Part column
  private List<PartEntity> partEntities = new ArrayList<>();



    // Foreign key column of Animal
    @ManyToOne // this mean many parts can be from one animal
    @JoinColumn (name = "animal_id", nullable=false)
    private AnimalEntity animalEntity; // the id of animal_id is possible to be repeated in this table


    // Foreign key column of Tray
    @ManyToOne // this mean many parts can be in one tray
    @JoinColumn(name = "part_id", nullable = true)
    private PartEntity partEntity; // the id of tray_id is possible to be repeated in this table
  private double currentWeight;

  public TrayEntity(){}

    public TrayEntity(double maxWeight, AnimalEntity animalEntity, PartEntity partEntity)
    {
        this.maxWeight = maxWeight;
        this.animalEntity = animalEntity;
        this.partEntity = partEntity;
    }

    public AnimalEntity getAnimal()
    {
        return animalEntity;
    }

    public void setAnimal(AnimalEntity animalEntity)
    {
        this.animalEntity = animalEntity;
    }

    public PartEntity getPart()
    {
        return partEntity;
    }


  public double getMaxWeight()
  {
    return maxWeight;
  }

    public int getId()
    {
        return id;
    }

    public double getCurrentWeight()
  {
    return currentWeight;
  }


    public List<PartEntity> getParts()
  {
    return List.copyOf(partEntities);
  }

    public void setPart(PartEntity partEntity)
    {
        this.partEntity = partEntity;
    }

  public void setMaxWeight(double maxWeight)
  {
    this.maxWeight = maxWeight;
  }

  public void setCurrentWeight(double currentWeight)
  {
    this.currentWeight = currentWeight;
  }

}
