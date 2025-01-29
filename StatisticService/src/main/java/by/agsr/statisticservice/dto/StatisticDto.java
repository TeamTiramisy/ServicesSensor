package by.agsr.statisticservice.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class StatisticDto {

    Integer id;
    Map<String, Integer> data;
}
