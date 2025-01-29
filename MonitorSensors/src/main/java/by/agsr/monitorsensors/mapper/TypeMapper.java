package by.agsr.monitorsensors.mapper;

import by.agsr.monitorsensors.dto.TypeCreateDto;
import by.agsr.monitorsensors.dto.TypeDto;
import by.agsr.monitorsensors.entity.Type;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface TypeMapper {

    TypeDto mapToDto(Type type);

    Type mapToEntity(TypeCreateDto typeCreateDto);

    Type update(@MappingTarget Type type, TypeCreateDto typeCreateDto);
}
