package ru.kifor4ik.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Visit {
    private String solution;
    private String cause;
    private Long registrationId;
}
