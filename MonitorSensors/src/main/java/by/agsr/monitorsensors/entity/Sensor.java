package by.agsr.monitorsensors.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@Builder
@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String model;
    private Range range;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Type type;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Unit unit;
    private String location;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createDate;

    @PrePersist
    public void prePersist() {
        setCreateDate(LocalDate.now());
    }

}
