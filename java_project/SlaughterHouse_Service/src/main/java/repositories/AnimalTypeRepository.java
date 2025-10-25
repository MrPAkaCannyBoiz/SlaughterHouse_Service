package repositories;

import entities.AnimalTypeEntity;
import generated.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTypeRepository extends JpaRepository<AnimalTypeEntity, Integer>
{
}
