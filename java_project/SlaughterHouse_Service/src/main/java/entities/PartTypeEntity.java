package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "part_type")
public class PartTypeEntity
{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String typeName;

    public PartTypeEntity()
    {
    }

    public PartTypeEntity(String typeName)
    {
        this.typeName = typeName;
    }

    public int getId()
    {
        return id;
    }


    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
}
