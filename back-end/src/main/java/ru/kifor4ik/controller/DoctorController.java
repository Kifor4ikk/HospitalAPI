package ru.kifor4ik.controller;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.dto.DoctorDto;
import ru.kifor4ik.entity.Doctor;
import ru.kifor4ik.service.DoctorService;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("api/v3/doctor")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/new")
    public void create(@RequestBody Doctor doctor){
        doctorService.create(doctor);
    }

    @GetMapping
    public Doctor get(@RequestParam Long id){
        return doctorService.getById(id);
    }

    @PutMapping
    public void update(@RequestParam Long id, @RequestBody Doctor doctor){
        doctorService.update(id,doctor);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id){
        doctorService.deleteStatus(id, true);
    }

    @GetMapping("/list")
    public List<Doctor> getAll(){
        return doctorService.getAll();
    }

    @GetMapping("/quality")
    public List<String> getAllQualities(){
        return doctorService.getAllQuality();
    }

    @GetMapping("/allQualityDoctors")
    public List<Doctor> getAllQualityDoctors(@RequestParam String quality){
        return doctorService.getAllQualityDoctors(quality);
    }

    @GetMapping("/nameAndSurname")
    public List<Doctor> getByNameAndSurname(@RequestParam String name,@RequestParam String surname){
        return doctorService.getByFullName(name,surname);
    }

}
