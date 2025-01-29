package by.agsr.statisticservice.service;

import by.agsr.statisticservice.dto.SensorDto;
import by.agsr.statisticservice.dto.StatisticDto;

import java.util.List;

public interface StatisticService {

    List<StatisticDto> findAll();
    void save();
}
