package ru.kifor4ik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.dto.RegistrationVisitDto;
import ru.kifor4ik.dto.Visit;
import ru.kifor4ik.entity.DayOfWeek;
import ru.kifor4ik.entity.Doctor;
import ru.kifor4ik.exception.BadDateException;
import ru.kifor4ik.exception.NotFoundException;
import ru.kifor4ik.repository.VisitRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class VisitService {

    private VisitRepository visitRepository;
    private DoctorService doctorService;
    private PatientService patientService;

    @Autowired
    public VisitService(VisitRepository visitRepository, DoctorService doctorService, PatientService patientService) {
        this.visitRepository = visitRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public void createRegistration(Date date, String cardNumber, Long doctorId) {
        RegistrationVisitDto dto = new RegistrationVisitDto();

        if (date.before(java.sql.Date.valueOf(LocalDate.now())))
            throw new BadDateException("Bad date");
        if (patientService.getByCardNumber(cardNumber) == null)
            throw new NotFoundException("Patient with cardnumber " + cardNumber + " was not found");
        dto.setPatientCard(cardNumber);

        Doctor doctor = doctorService.getById(doctorId);
        if (doctor == null)
            throw new NotFoundException("Doctor with id " + doctorId + " was not found");
        dto.setDoctorId(doctorId);
        String day = "UNDEFINED";
        switch (date.getDay()){
            case 0: {day = "Monday"; break;}
            case 1: {day = "Tuesday"; break;}
            case 2: {day = "Wednesday"; break;}
            case 3: {day = "Thursday"; break;}
            case 4: {day = "Friday"; break;}
            case 5: {day = "Saturday"; break;}
            case 6: {day = "Sunday"; break;}
        }
        if(!doctor.getWorkDays().contains(DayOfWeek.valueOf(day)))
            throw new BadDateException("Doctor don't work at " + day);
        visitRepository.save(dto);
    }

    public void createVisit(Visit visit) {
        RegistrationVisitDto dto = visitRepository.getById(visit.getRegistrationId());
        if (dto.getId() == 0)
            throw new NotFoundException("Not found registration with current ID");
        dto.setCause(visit.getCause());
        dto.setSolution(visit.getSolution());
        dto.setIsDone(true);
        visitRepository.save(dto);
    }

    public RegistrationVisitDto getById(Long id){
        return visitRepository.getById(id);
    }

    public List<RegistrationVisitDto> getAll(){
        return visitRepository.findAll();
    }

}
