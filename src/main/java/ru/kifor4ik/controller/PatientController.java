package ru.kifor4ik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.dto.NewPatientDto;
import ru.kifor4ik.dto.PatientShow;
import ru.kifor4ik.service.PatientCardService;
import ru.kifor4ik.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("api/v3/patient")
public class PatientController {

    private PatientService patientService;
    private PatientCardService patientCardService;

    @Autowired
    public PatientController(PatientService patientService, PatientCardService patientCardService) {
        this.patientService = patientService;
        this.patientCardService = patientCardService;
    }

    @PostMapping("/new")
    public void create(@RequestBody NewPatientDto patient){
        patientService.create(patient);
    }

    @GetMapping
    public PatientShow get(@RequestParam Long id){
        return PatientShow.toShow(patientService.getById(id));
    }

    @GetMapping("/getByCardNumber")
    public PatientShow getByCardNumber(@RequestParam String cardNumber){
        return PatientShow.toShow(patientService.getByCardNumber(cardNumber));
    }

    @PutMapping
    public void update(@RequestBody NewPatientDto newPatientDto){
        patientService.update(newPatientDto);
    }

    @DeleteMapping
    public void delete(@RequestParam String cardNumber){
        patientService.delete(cardNumber,true);
    }

    @GetMapping("/list")
    public List<PatientShow> getAll(){
        return patientService.getAll();
    }

    @GetMapping("/getByNameAndSurname")
    public List<PatientShow> getByNameAndSurname(@RequestParam String name, @RequestParam String surname){
        return patientService.getByNameAndSurname(name,surname);
    }

    @GetMapping("/getListWithCause")
    public List<PatientShow> getByCause(@RequestParam String cause){
        return patientService.getAllWithCurrentCause(cause);
    }

}
