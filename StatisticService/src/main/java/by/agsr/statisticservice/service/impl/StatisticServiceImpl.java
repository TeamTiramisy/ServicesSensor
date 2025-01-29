package by.agsr.statisticservice.service.impl;

import by.agsr.statisticservice.dto.SensorDto;
import by.agsr.statisticservice.dto.StatisticDto;
import by.agsr.statisticservice.entity.Statistic;
import by.agsr.statisticservice.mapper.StatisticMapper;
import by.agsr.statisticservice.repository.StatisticRepository;
import by.agsr.statisticservice.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticServiceImpl implements StatisticService {

    @Value("${sensors.username}")
    private String username;
    @Value("${sensors.password}")
    private String password;
    @Value("${sensors.url}")
    private String url;
    private final RestTemplate restTemplate;
    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;


    @Override
    public List<StatisticDto> findAll() {
        return statisticRepository.findAll().stream()
                .map(statisticMapper::mapToDto)
                .toList();
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 2 * * ?")
    public void save() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<SensorDto> body = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<SensorDto>>() {
                }
        ).getBody();

        statisticRepository.deleteAll();

        Map<String, Integer> data = body.stream()
                .collect(Collectors.groupingBy(SensorDto::getType,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));

        Statistic statistic = new Statistic(null, data);

        statisticRepository.save(statistic);
    }
}
