package by.agsr.monitorsensors.mapper;

import by.agsr.monitorsensors.dto.SensorDto;
import by.agsr.monitorsensors.entity.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SensorMapper {

    @Mapping(source = "type.name", target = "type")
    @Mapping(source = "unit.name", target = "unit")
    SensorDto mapToDto(Sensor sensor);
}
