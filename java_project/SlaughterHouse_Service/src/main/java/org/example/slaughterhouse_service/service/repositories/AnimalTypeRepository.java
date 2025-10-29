package org.example.slaughterhouse_service.service.repositories;

import org.example.slaughterhouse_service.entities.AnimalTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTypeRepository extends JpaRepository<AnimalTypeEntity, Integer>
{
}
