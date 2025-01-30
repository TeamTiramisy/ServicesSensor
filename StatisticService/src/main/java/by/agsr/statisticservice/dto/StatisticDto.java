package by.agsr.statisticservice.dto;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticDto {

    private Map<String, Integer> data;
}
