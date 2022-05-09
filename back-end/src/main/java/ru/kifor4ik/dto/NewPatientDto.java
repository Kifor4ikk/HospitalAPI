package ru.kifor4ik.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class NewPatientDto {
    @NotBlank
    @Size(max = 10, min = 1)
    private String patientCard;
    @NotBlank
    @Size(max = 50, min = 2)
    private String name;
    @NotBlank
    @Size(max = 50, min = 2)
    private String surname;
    @PositiveOrZero
    private Integer age;


    public PatientDto patientDto(){
        PatientDto patientDto = new PatientDto();
        patientDto.setAge(this.getAge());
        patientDto.setName(this.getName());
        patientDto.setSurname(this.getSurname());
        return patientDto;
    }
}
