package ru.kifor4ik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kifor4ik.entity.PatientCard;

public interface PatientCardRepository extends JpaRepository<PatientCard, String>{

}
