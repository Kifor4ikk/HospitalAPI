package ru.kifor4ik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.dto.NewPatientDto;
import ru.kifor4ik.dto.PatientDto;
import ru.kifor4ik.dto.PatientShow;
import ru.kifor4ik.entity.PatientCard;
import ru.kifor4ik.exception.AlreadyExistException;
import ru.kifor4ik.exception.NotFoundException;
import ru.kifor4ik.repository.PatientCardRepository;
import ru.kifor4ik.repository.PatientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private PatientCardRepository patientCardRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientCardRepository patientCardRepository) {
        this.patientRepository = patientRepository;
        this.patientCardRepository = patientCardRepository;
    }

    public void create(NewPatientDto newPatient) {
        if (patientRepository.findByPatientCard_cardNumber(newPatient.getPatientCard()) != null)
            throw new AlreadyExistException("Patient with current card number already exist");
        PatientCard patientCard = patientCardRepository.save(new PatientCard(newPatient.getPatientCard()));
        PatientDto patientDto = newPatient.patientDto();
        patientDto.setPatientCard(patientCard);
        patientCard = patientCardRepository.getById(newPatient.getPatientCard());
        patientCard.setPatient(patientDto);
        patientRepository.save(patientDto);
        patientCardRepository.save(patientCard);
    }

    public PatientDto getById(Long id) {
        return patientRepository.getById(id);
    }

    public PatientDto getByCardNumber(String cardNumber) {
        PatientDto patientDto = patientRepository.findByPatientCard_cardNumber(cardNumber);
        if (patientDto == null)
            throw new NotFoundException("Patient with current card number was not found");
        return patientDto;
    }

    public void update(NewPatientDto newPatientDto) {
        PatientDto patientDtoTemp = getByCardNumber(newPatientDto.getPatientCard());
        if (patientDtoTemp == null)
            throw new NotFoundException("Patient with current card number was not found");
        patientDtoTemp.setName(newPatientDto.getName());
        patientDtoTemp.setSurname(newPatientDto.getSurname());
        patientDtoTemp.setAge(newPatientDto.getAge());
        patientRepository.save(patientDtoTemp);
    }

    public void delete(String cardNumber, boolean isDeleted) {
        PatientDto patientDtoTemp = getByCardNumber(cardNumber);
        if (patientDtoTemp == null)
            throw new NotFoundException("Patient with current card number was not found");
        patientDtoTemp.setDeleted(isDeleted);
        patientRepository.save(patientDtoTemp);
    }

    public List<PatientShow> getByNameAndSurname(String name, String surname) {
        return patientRepository.findByNameAndSurname(name, surname).stream().map(PatientShow::toShow).collect(Collectors.toList());
    }

    public List<PatientShow> getAll() {
        return patientRepository.findAll().stream().map(PatientShow::toShow).collect(Collectors.toList());
    }

    public List<PatientShow> getAllWithCurrentCause(String cause) {
        List<PatientDto> all = patientRepository.findAll();
        List<PatientDto> temp = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            for(int j = 0; j < all.get(i).getPatientCard().getVisitList().size(); j++){
                if(all.get(i).getPatientCard().getVisitList().get(j).getCause().contains(cause)){
                    temp.add(all.get(i));
                    break;
                }
            }
        }
        return temp.stream().map(PatientShow::toShow).collect(Collectors.toList());
    }
}
