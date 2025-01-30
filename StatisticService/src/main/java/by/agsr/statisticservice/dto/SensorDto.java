package by.agsr.statisticservice.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDto {

    private Integer id;
    private String name;
    private String model;
    private Range range;
    private String type;
    private String unit;
    private String location;
    private String description;
    private LocalDate createDate;
}
