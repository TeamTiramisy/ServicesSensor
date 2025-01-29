package by.agsr.statisticservice.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Range {

    private Integer rangeFrom;
    private Integer rangeTo;
}
