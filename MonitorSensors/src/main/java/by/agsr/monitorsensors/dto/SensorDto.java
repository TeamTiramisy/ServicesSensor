package by.agsr.monitorsensors.dto;

import by.agsr.monitorsensors.entity.Range;
import lombok.*;

import java.time.LocalDate;

@Value
@Builder
public class SensorDto {

    Integer id;
    String name;
    String model;
    Range range;
    String type;
    String unit;
    String location;
    String description;
    LocalDate createDate;
}
