package ru.kifor4ik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kifor4ik.dto.DoctorDto;

import java.util.List;

public interface DoctorRepository extends JpaRepository<DoctorDto, Long> {

    List<DoctorDto> findByQuality(String quality);
    List<DoctorDto> findByNameAndSurname(String name, String surname);
}
