package by.agsr.monitorsensors.repository;

import by.agsr.monitorsensors.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    List<Sensor> findByNameContainingOrModelContaining(String name, String model);
}
