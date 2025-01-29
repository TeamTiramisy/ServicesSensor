package by.agsr.statisticservice.mapper;

import by.agsr.statisticservice.dto.StatisticDto;
import by.agsr.statisticservice.entity.Statistic;
import org.mapstruct.Mapper;

@Mapper
public interface StatisticMapper {

    StatisticDto mapToDto(Statistic statistic);
}
