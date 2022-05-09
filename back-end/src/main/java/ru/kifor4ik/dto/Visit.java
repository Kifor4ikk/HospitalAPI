package ru.kifor4ik.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Data
public class Visit {
    private String solution;
    private String cause;
    private Long registrationId;
}
