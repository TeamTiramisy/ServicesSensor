package by.agsr.monitorsensors.service;


import by.agsr.monitorsensors.dto.TypeCreateDto;
import by.agsr.monitorsensors.dto.TypeDto;

import java.util.List;

public interface TypeService {

    List<TypeDto> findAll();

    TypeDto findById(Integer id);

    TypeDto findByName(String name);

    TypeDto create(TypeCreateDto typeCreateDto);

    TypeDto update(Integer id, TypeCreateDto typeCreateDto);

    void delete(Integer id);
}
