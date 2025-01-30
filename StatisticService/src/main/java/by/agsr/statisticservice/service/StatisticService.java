package by.agsr.statisticservice.service;

import by.agsr.statisticservice.dto.StatisticDto;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService {

    List<StatisticDto> findAll();
    void save();
    StatisticDto getSensorsByDateRange(LocalDate from, LocalDate to);
}
