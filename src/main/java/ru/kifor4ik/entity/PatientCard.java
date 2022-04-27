package ru.kifor4ik.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ru.kifor4ik.dto.PatientDto;
import ru.kifor4ik.dto.RegistrationVisitDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Data
public class PatientCard {

    public PatientCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Id
    private String cardNumber;
    @OneToOne
    private PatientDto patient;
    @OneToMany
    private List<RegistrationVisitDto> visitList = new ArrayList<>();
}
