package by.agsr.monitorsensors.service.impl;

import by.agsr.monitorsensors.dto.UnitCreateDto;
import by.agsr.monitorsensors.dto.UnitDto;
import by.agsr.monitorsensors.entity.Unit;
import by.agsr.monitorsensors.exception.ResourceNotFoundException;
import by.agsr.monitorsensors.mapper.UnitMapper;
import by.agsr.monitorsensors.repository.UnitRepository;
import by.agsr.monitorsensors.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    @Override
    public List<UnitDto> findAll() {
        return unitRepository.findAll().stream()
                .map(unitMapper::mapToDto)
                .toList();
    }

    @Override
    public UnitDto findById(Integer id) {
        return unitRepository.findById(id)
                .map(unitMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + id));
    }

    @Override
    public UnitDto findByName(String name) {
        return unitRepository.findByName(name)
                .map(unitMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with name: " + name));
    }

    @Override
    @Transactional
    public UnitDto create(UnitCreateDto unitCreateDto) {
        return Optional.of(unitCreateDto)
                .map(unitMapper::mapToEntity)
                .map(unitRepository::save)
                .map(unitMapper::mapToDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public UnitDto update(Integer id, UnitCreateDto unitCreateDto) {
        return unitRepository.findById(id)
                .map(type -> unitMapper.update(type, unitCreateDto))
                .map(unitRepository::saveAndFlush)
                .map(unitMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + id));

        unitRepository.delete(unit);
    }
}
