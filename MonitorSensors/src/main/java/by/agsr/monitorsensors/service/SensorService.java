package by.agsr.monitorsensors.service;

import by.agsr.monitorsensors.dto.SensorCreateDto;
import by.agsr.monitorsensors.dto.SensorDto;

import java.util.List;

public interface SensorService {

    List<SensorDto> findAll();

    List<SensorDto> findByNameOrModel(String name, String model);

    SensorDto findById(Integer id);

    SensorDto create(SensorCreateDto sensorCreateDto);

    SensorDto update(Integer id, SensorCreateDto sensorCreateDto);

    void delete(Integer id);
}
