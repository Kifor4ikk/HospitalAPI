package ru.kifor4ik.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kifor4ik.entity.PatientCard;
import ru.kifor4ik.entity.Visit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientShow {

    private Long id;
    private String cardNumber;
    private List<RegistrationVisitDto> visitList = new ArrayList<>();
    private String name;
    private String surname;
    private Integer age;
    private boolean isDeleted = false;

    public static PatientShow toShow(PatientDto patientDto){
        return PatientShow.builder()
                .id(patientDto.getId())
                .cardNumber(patientDto.getPatientCard().getCardNumber())
                .visitList(patientDto.getPatientCard().getVisitList())
                .name(patientDto.getName())
                .surname(patientDto.getSurname())
                .age(patientDto.getAge())
                .isDeleted(patientDto.isDeleted())
                .build();
    }
}
