package ru.kifor4ik.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Data
public class Visit extends Registration {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    @Size(max = 1000)
    private String cause;
    @NotBlank
    @Size(max = 1000)
    private String solution;
    @NotBlank
    private Boolean isDone;
}
