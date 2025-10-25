package repositories;

import entities.TrayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrayRepository extends JpaRepository<TrayEntity, Integer>
{
}
