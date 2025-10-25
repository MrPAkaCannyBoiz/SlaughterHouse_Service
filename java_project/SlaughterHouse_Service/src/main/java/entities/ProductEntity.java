package entities;

import jakarta.persistence.*;

//given that tray know animal and part via FK, use tray to pack the product
// therefore, we need key of those two as FK and total weight
@Entity
@Table(name = "product")
public class ProductEntity
{
    //PK
  @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
  private int id;
  // FK attribute, either single part of half animal (two individual parts), use id, with its table
    // will be
  @ManyToOne
  @JoinColumn(name = "tray_id_1", nullable = false)
  private TrayEntity trayEntity;

    @ManyToOne
  @JoinColumn(name = "tray_id_2", nullable = true)
  private TrayEntity trayEntity2; // for double part product, can be null for single part product

    @Column (name = "total_weight", nullable = false)
    private double totalWeight;

    //constructor with tray parameters (assign FK, PK assign by database automatically)
    // for single part product
    public ProductEntity(TrayEntity trayEntity)
    {
        setTray(trayEntity);
        setTotalWeight(0);
    }

    public ProductEntity(TrayEntity trayEntity, TrayEntity trayEntity2)
    {
        setTray(trayEntity);
        setTray2(trayEntity2);
        setTotalWeight(0);
    }

    public ProductEntity()
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

    public double getTotalWeight()
    {
        return totalWeight;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTray(TrayEntity trayEntity)
    {
        this.trayEntity = trayEntity;
    }

    public void setTotalWeight(double totalWeight)
    {
        this.totalWeight = totalWeight;
    }

    public TrayEntity getTray2()
    {
        return trayEntity2;
    }

    public void setTray2(TrayEntity trayEntity2)
    {
        this.trayEntity2 = trayEntity2;
    }
}
