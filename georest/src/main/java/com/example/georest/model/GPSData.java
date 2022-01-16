package com.example.georest.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class GPSData {

    @Id
    @SequenceGenerator(
            name = "gpsdatalist_sequence",
            sequenceName = "gpsdatalist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "gpsdatalist_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long deviceId;

    @NotNull
    @Column(nullable = false)
    private Double longitude;

    @NotNull
    @Column(nullable = false)
    private Double latitude;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime localDateTime;
}
