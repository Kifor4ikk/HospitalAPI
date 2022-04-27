package ru.kifor4ik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
