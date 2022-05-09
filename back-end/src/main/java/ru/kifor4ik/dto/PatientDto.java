package ru.kifor4ik.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ru.kifor4ik.entity.PatientCard;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Data
public class PatientDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private PatientCard patientCard;
    @NotBlank
    @Size(max = 50, min = 2)
    private String name;
    @NotBlank
    @Size(max = 50, min = 2)
    private String surname;
    @PositiveOrZero
    private Integer age;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isDeleted = false;
}
