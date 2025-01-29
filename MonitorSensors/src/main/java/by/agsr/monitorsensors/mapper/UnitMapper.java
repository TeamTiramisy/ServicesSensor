package by.agsr.monitorsensors.mapper;

import by.agsr.monitorsensors.dto.UnitCreateDto;
import by.agsr.monitorsensors.dto.UnitDto;
import by.agsr.monitorsensors.entity.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface UnitMapper {

    UnitDto mapToDto(Unit unit);

    Unit mapToEntity(UnitCreateDto unitCreateDto);

    Unit update(@MappingTarget Unit unit, UnitCreateDto unitCreateDto);
}
