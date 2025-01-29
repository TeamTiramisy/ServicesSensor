package by.agsr.statisticservice.controller;

import by.agsr.statisticservice.dto.StatisticDto;
import by.agsr.statisticservice.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/sensors")
@RequiredArgsConstructor
@Tag(name = "Statistic", description = "Statistic API")
public class StatisticController {

    private final StatisticService service;

    @GetMapping
    @Operation(summary = "Find all")
    public ResponseEntity<List<StatisticDto>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
