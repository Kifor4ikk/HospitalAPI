package ru.kifor4ik.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Validated
@Data
public abstract class Human {
    @NotBlank
    @Size(max = 50, min = 2)
    private String name;
    @NotBlank
    @Size(max = 50, min = 2)
    private String surname;
    @PositiveOrZero
    private Integer age;
}
