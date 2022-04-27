package ru.kifor4ik.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ru.kifor4ik.entity.DayOfWeek;
import ru.kifor4ik.entity.Doctor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Data
public class DoctorDto {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 50, min = 2)
    private String name;
    @NotBlank
    @Size(max = 50, min = 2)
    private String surname;
    @PositiveOrZero
    private Integer age;
    @NotBlank
    @Size(max = 100, min = 3)
    private String quality;
    private String workDays;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isDeleted = false;

    public static Doctor toDoctor(DoctorDto dto){
        Doctor doctor = new Doctor();
        doctor.setId(dto.getId());
        doctor.setName(dto.getName());
        doctor.setSurname(dto.getSurname());
        doctor.setAge(dto.getAge());
        doctor.setQuality(dto.getQuality());
        List<String> list = Arrays.stream(dto.getWorkDays().substring(1, dto.getWorkDays().length() - 1).split(", ")).collect(Collectors.toList());
        for(int i = 0; i < list.size(); i++)
            doctor.getWorkDays().add(DayOfWeek.valueOf(list.get(i)));
        doctor.setDeleted(dto.isDeleted());
        return doctor;
    }

    public static DoctorDto toDto(Doctor doctor){
        DoctorDto dto = DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .age(doctor.getAge())
                .quality(doctor.getQuality())
                .isDeleted(doctor.isDeleted())
                .build();
        dto.setWorkDays(Arrays.toString(doctor.getWorkDays().stream()
                .map(DayOfWeek::name).toArray()));
        return dto;
    }
}
