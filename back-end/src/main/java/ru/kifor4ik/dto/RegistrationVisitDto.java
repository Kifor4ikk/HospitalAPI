package ru.kifor4ik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ru.kifor4ik.entity.Doctor;
import ru.kifor4ik.entity.PatientCard;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Data
public class RegistrationVisitDto {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String patientCard;
    private Long doctorId;
    @Size(max = 1000)
    private String cause;
    @Size(max = 1000)
    private String solution;
    private Boolean isDone;
}
