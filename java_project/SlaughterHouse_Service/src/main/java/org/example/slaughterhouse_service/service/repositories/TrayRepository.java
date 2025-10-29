package org.example.slaughterhouse_service.service.repositories;

import org.example.slaughterhouse_service.entities.TrayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrayRepository extends JpaRepository<TrayEntity, Integer>
{
}
