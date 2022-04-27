package ru.kifor4ik.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ru.kifor4ik.dto.DoctorDto;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Validated
@Data
public class Registration {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    private Date date;
    @NotNull
    @OneToOne
    private PatientCard patientCard;
    @NotNull
    @OneToOne
    @JoinColumn(name = "id")
    private DoctorDto doctor;
}
