package org.example.slaughterhouse_service.service.repositories;

import org.example.slaughterhouse_service.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer>
{
}
