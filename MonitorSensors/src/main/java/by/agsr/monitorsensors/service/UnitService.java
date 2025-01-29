package by.agsr.monitorsensors.service;


import by.agsr.monitorsensors.dto.UnitCreateDto;
import by.agsr.monitorsensors.dto.UnitDto;

import java.util.List;

public interface UnitService {

    List<UnitDto> findAll();

    UnitDto findById(Integer id);

    UnitDto findByName(String name);

    UnitDto create(UnitCreateDto unitCreateDto);

    UnitDto update(Integer id, UnitCreateDto unitCreateDto);

    void delete(Integer id);
}
