package by.agsr.monitorsensors.repository;

import by.agsr.monitorsensors.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

    Optional<Unit> findByName(String name);
}
