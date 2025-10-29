package org.example.slaughterhouse_service.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "animal_type")
public class AnimalTypeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "name", nullable = false, unique = true)
    private String name;

    public AnimalTypeEntity()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

}
