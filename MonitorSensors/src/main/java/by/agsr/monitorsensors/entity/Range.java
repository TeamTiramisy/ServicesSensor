package by.agsr.monitorsensors.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Range {

    private Integer rangeFrom;
    private Integer rangeTo;
}
