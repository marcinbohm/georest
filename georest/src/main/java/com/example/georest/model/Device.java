package com.example.georest.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Devices")
public class Device {

    @Id
    @SequenceGenerator(
            name = "devicelist_sequence",
            sequenceName = "devicelist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "devicelist_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(
            name = "device",
            referencedColumnName = "id",
            insertable = false,
            updatable = false,
            foreignKey = @javax.persistence
                    .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private List<GPSData> gpsDataList;
}
