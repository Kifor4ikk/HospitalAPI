package ru.kifor4ik.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Data
public class Doctor extends Human {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    @Size(max = 100, min = 3)
    private String quality;
    @ElementCollection
    private final Set<DayOfWeek> workDays = new HashSet<>();
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isDeleted = false;
}
