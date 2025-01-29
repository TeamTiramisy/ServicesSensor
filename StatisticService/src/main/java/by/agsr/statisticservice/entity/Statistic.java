package by.agsr.statisticservice.entity;

import by.agsr.statisticservice.convertor.JsonToMapConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = JsonToMapConverter.class)
    private Map<String, Integer> data;
}
