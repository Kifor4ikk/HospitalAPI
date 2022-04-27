package ru.kifor4ik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kifor4ik.dto.RegistrationVisitDto;

public interface VisitRepository extends JpaRepository<RegistrationVisitDto, Long> {


}
