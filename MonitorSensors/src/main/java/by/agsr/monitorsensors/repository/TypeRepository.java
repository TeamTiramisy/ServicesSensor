package by.agsr.monitorsensors.repository;

import by.agsr.monitorsensors.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Integer> {

    Optional<Type> findByName(String name);
}
