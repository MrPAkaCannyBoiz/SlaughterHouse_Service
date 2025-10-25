package entities;

import jakarta.persistence.*;

@Entity
@Table (name = "part")
public class PartEntity
{
    @Id @GeneratedValue (strategy=GenerationType.IDENTITY)
  private int id;
  // Foreign key column of Animal
  @ManyToOne // this mean many parts can be from one animal
  @JoinColumn (name = "animal_id", nullable=false)
  // the id of animal_id is possible to be repeated in this table
  private AnimalEntity animalEntity;
  @Column (name = "weight", nullable=false)
  private double weight;
  // Foreign key column of Tray
  @ManyToOne // this mean many parts can be in one tray
  @JoinColumn(name = "tray_id", nullable = true)
  // the id of tray_id is possible to be repeated in this table
  private TrayEntity trayEntity;
  @ManyToOne //FK to PartType
  @JoinColumn(name = "part_type_id", nullable = false)
  private PartTypeEntity partTypeEntity;

  public PartEntity(AnimalEntity animalEntity, double weight, TrayEntity trayEntity, PartTypeEntity partTypeEntity)
  {
    this.animalEntity = animalEntity;
    this.weight = weight;
    this.trayEntity = trayEntity;
    setPartType(partTypeEntity);
  }

  public PartEntity()
  {

  }

    public int getId()
    {
        return id;
    }

    public TrayEntity getTray()
    {
        return trayEntity;
    }

    public void setTray(TrayEntity trayEntity)
    {
        this.trayEntity = trayEntity;
    }

    public AnimalEntity getAnimal()
    {
        return animalEntity;
    }

    public void setAnimal(AnimalEntity animalEntity)
    {
        this.animalEntity = animalEntity;
    }

    public double getWeight()
  {
    return this.weight;
  }


    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public PartTypeEntity getPartType()
    {
        return partTypeEntity;
    }

    public void setPartType(PartTypeEntity partTypeEntity)
    {
        this.partTypeEntity = partTypeEntity;
    }


}
