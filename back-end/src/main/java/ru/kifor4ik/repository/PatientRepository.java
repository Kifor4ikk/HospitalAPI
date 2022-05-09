package ru.kifor4ik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kifor4ik.dto.PatientDto;

import java.util.List;

public interface PatientRepository extends JpaRepository<PatientDto, Long> {

    PatientDto findByPatientCard_cardNumber(String cardNumber);
    List<PatientDto> findByNameAndSurname(String name, String surname);
}
