package by.agsr.monitorsensors.dto;

import by.agsr.monitorsensors.validation.annotation.SensorInfo;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SensorInfo
public class SensorCreateDto {

    @Size(min = 3, max = 30)
    private String name;
    @Size(min = 1, max = 15)
    private String model;
    @Positive
    private Integer from;
    @Positive
    private Integer to;
    private Integer typeId;
    private Integer unitId;
    @Size(max = 40)
    private String location;
    @Size(max = 200)
    private String description;
}
