package repositories;

import entities.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartsRepository extends JpaRepository<PartEntity, Integer>
{
  // Spring Data will automatically generate a query like:
  // SELECT * FROM part WHERE animal_id = ?;
}
