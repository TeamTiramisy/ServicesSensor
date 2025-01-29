package by.agsr.monitorsensors.service.impl;

import by.agsr.monitorsensors.dto.TypeCreateDto;
import by.agsr.monitorsensors.dto.TypeDto;
import by.agsr.monitorsensors.entity.Type;
import by.agsr.monitorsensors.exception.ResourceNotFoundException;
import by.agsr.monitorsensors.mapper.TypeMapper;
import by.agsr.monitorsensors.repository.TypeRepository;
import by.agsr.monitorsensors.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    @Override
    public List<TypeDto> findAll() {
        return typeRepository.findAll().stream()
                .map(typeMapper::mapToDto)
                .toList();
    }

    @Override
    public TypeDto findById(Integer id) {
        return typeRepository.findById(id)
                .map(typeMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with id: " + id));
    }

    @Override
    public TypeDto findByName(String name) {
        return typeRepository.findByName(name)
                .map(typeMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with name: " + name));
    }

    @Override
    @Transactional
    public TypeDto create(TypeCreateDto typeCreateDto) {
        return Optional.of(typeCreateDto)
                .map(typeMapper::mapToEntity)
                .map(typeRepository::save)
                .map(typeMapper::mapToDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public TypeDto update(Integer id, TypeCreateDto typeCreateDto) {
        return typeRepository.findById(id)
                .map(type -> typeMapper.update(type, typeCreateDto))
                .map(typeRepository::saveAndFlush)
                .map(typeMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with id: " + id));

        typeRepository.delete(type);
    }
}
