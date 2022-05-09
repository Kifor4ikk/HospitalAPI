package ru.kifor4ik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.dto.PatientDto;
import ru.kifor4ik.dto.RegistrationVisitDto;
import ru.kifor4ik.dto.Visit;
import ru.kifor4ik.entity.DayOfWeek;
import ru.kifor4ik.entity.Doctor;
import ru.kifor4ik.exception.AlreadyExistException;
import ru.kifor4ik.exception.BadDateException;
import ru.kifor4ik.exception.NotFoundException;
import ru.kifor4ik.repository.PatientRepository;
import ru.kifor4ik.repository.VisitRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class VisitService {

    private VisitRepository visitRepository;
    private PatientRepository patientRepository;
    private DoctorService doctorService;
    private PatientService patientService;

    @Autowired
    public VisitService(VisitRepository visitRepository, PatientRepository patientRepository, DoctorService doctorService, PatientService patientService) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
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

        if (visitRepository.findAllByDoctorIdAndDate(doctorId, date).size() > 8)
            throw new AlreadyExistException("Today doctor already is busy");
        if (!visitRepository.findAllByDoctorIdAndDateAndPatientCard(doctorId, date, cardNumber).isEmpty())
            throw new AlreadyExistException("This person already registered to this day and this doctor");
        String day = "UNDEFINED";

        switch (date.getDay()) {
            case 1: {
                day = "Monday";
                break;
            }
            case 2: {
                day = "Tuesday";
                break;
            }
            case 3: {
                day = "Wednesday";
                break;
            }
            case 4: {
                day = "Thursday";
                break;
            }
            case 5: {
                day = "Friday";
                break;
            }
            case 6: {
                day = "Saturday";
                break;
            }
            case 0: {
                day = "Sunday";
                break;
            }
        }

        if (!doctor.getWorkDays().contains(DayOfWeek.valueOf(day)))
            throw new BadDateException("Doctor don't work at " + day);
        dto.setDate(date);
        //patient save
        PatientDto patient = patientService.getByCardNumber(cardNumber);
        patient.getPatientCard().getVisitList().add(dto);
        visitRepository.save(dto);
        patientRepository.save(patient);
    }

    public void createVisit(Visit visit) {
        RegistrationVisitDto dto = getById(visit.getRegistrationId());
        if (dto.getId() == 0)
            throw new NotFoundException("Registration with current ID was not found!");
        if (dto.getCause() != null)
            throw new AlreadyExistException("Registration already exist!");
        dto.setCause(visit.getCause());
        dto.setSolution(visit.getSolution());
        dto.setIsDone(true);
        visitRepository.save(dto);
    }

    public RegistrationVisitDto getById(Long id) {
        if (visitRepository.findById(id).isEmpty())
            throw new NotFoundException("Registration with current ID was not found!");
        return visitRepository.findById(id).get();
    }

    public List<RegistrationVisitDto> getAll() {
        return visitRepository.findAll();
    }

}
