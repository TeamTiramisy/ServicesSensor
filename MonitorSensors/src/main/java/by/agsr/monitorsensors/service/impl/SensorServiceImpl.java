package by.agsr.monitorsensors.service.impl;

import by.agsr.monitorsensors.dto.SensorCreateDto;
import by.agsr.monitorsensors.dto.SensorDto;
import by.agsr.monitorsensors.entity.Range;
import by.agsr.monitorsensors.entity.Sensor;
import by.agsr.monitorsensors.entity.Type;
import by.agsr.monitorsensors.entity.Unit;
import by.agsr.monitorsensors.exception.ResourceNotFoundException;
import by.agsr.monitorsensors.mapper.SensorMapper;
import by.agsr.monitorsensors.repository.SensorRepository;
import by.agsr.monitorsensors.repository.TypeRepository;
import by.agsr.monitorsensors.repository.UnitRepository;
import by.agsr.monitorsensors.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final TypeRepository typeRepository;
    private final UnitRepository unitRepository;
    private final SensorMapper sensorMapper;

    @Override
    public List<SensorDto> findAll() {
        return sensorRepository.findAll().stream()
                .map(sensorMapper::mapToDto)
                .toList();
    }

    @Override
    public List<SensorDto> findByNameOrModel(String name, String model) {
        return sensorRepository.findByNameContainingOrModelContaining(name, model).stream()
                .map(sensorMapper::mapToDto)
                .toList();
    }

    @Override
    public SensorDto findById(Integer id) {
        return sensorRepository.findById(id)
                .map(sensorMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with id: " + id));
    }

    @Override
    @Transactional
    public SensorDto create(SensorCreateDto sensorCreateDto) {
        return Optional.of(createSeonsor(sensorCreateDto))
                .map(sensorRepository::save)
                .map(sensorMapper::mapToDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public SensorDto update(Integer id, SensorCreateDto sensorCreateDto) {
        return sensorRepository.findById(id)
                .map(sensor -> update(sensor, sensorCreateDto))
                .map(sensorMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with id: " + id));

        sensorRepository.delete(sensor);
    }

    private Sensor createSeonsor(SensorCreateDto sensorCreateDto) {
        Unit unit = null;

        Type type = typeRepository.findById(sensorCreateDto.getTypeId()).orElseThrow(() ->
                new ResourceNotFoundException("Type not found with id: " + sensorCreateDto.getTypeId()));

        if (sensorCreateDto.getUnitId() != null) {
            unit = unitRepository.findById(sensorCreateDto.getUnitId())
                    .orElse(null);
        }

        return Sensor.builder()
                .name(sensorCreateDto.getName())
                .model(sensorCreateDto.getModel())
                .range(new Range(sensorCreateDto.getFrom(), sensorCreateDto.getTo()))
                .type(type)
                .unit(unit)
                .location(sensorCreateDto.getLocation())
                .description(sensorCreateDto.getDescription())
                .build();
    }

    private Sensor update(Sensor sensor, SensorCreateDto sensorCreateDto) {
        if (!sensor.getType().getId().equals(sensorCreateDto.getTypeId())) {
            Type type = typeRepository.findById(sensorCreateDto.getTypeId()).orElseThrow(() ->
                    new ResourceNotFoundException("Type not found with id: " + sensorCreateDto.getTypeId()));

            sensor.setType(type);
        }

        if (sensorCreateDto.getUnitId() != null
            && (sensor.getUnit() == null || !sensor.getUnit().getId().equals(sensorCreateDto.getUnitId()))) {
            Unit unit = unitRepository.findById(sensorCreateDto.getUnitId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Unit not found with id: " + sensorCreateDto.getUnitId()));

            sensor.setUnit(unit);
        }

        if (sensorCreateDto.getUnitId() == null) {
            sensor.setUnit(null);
        }

        sensor.setName(sensorCreateDto.getName());
        sensor.setModel(sensorCreateDto.getModel());
        sensor.setRange(new Range(sensorCreateDto.getFrom(), sensorCreateDto.getTo()));
        sensor.setLocation(sensorCreateDto.getLocation());
        sensor.setDescription(sensorCreateDto.getDescription());

        return sensor;
    }
}
