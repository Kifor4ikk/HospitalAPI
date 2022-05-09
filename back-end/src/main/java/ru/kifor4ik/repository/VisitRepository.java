package ru.kifor4ik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kifor4ik.dto.RegistrationVisitDto;

import java.sql.Date;
import java.util.List;

public interface VisitRepository extends JpaRepository<RegistrationVisitDto, Long> {

    List<RegistrationVisitDto> findAllByDoctorIdAndDate(Long doctorId, Date date);
    List<RegistrationVisitDto> findAllByDoctorIdAndDateAndPatientCard(Long doctorId, Date date,String cardNumber);
}
