package ru.kifor4ik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.entity.PatientCard;
import ru.kifor4ik.repository.PatientCardRepository;

@Service
public class PatientCardService {

    private PatientCardRepository patientCardRepository;

    @Autowired
    public PatientCardService(PatientCardRepository patientCardRepository) {
        this.patientCardRepository = patientCardRepository;
    }

    public void create(String cardName, Long patientId){
        this.patientCardRepository.save(new PatientCard());
    }

    public PatientCard get(String cardId){
        return patientCardRepository.getById(cardId);
    }
}
