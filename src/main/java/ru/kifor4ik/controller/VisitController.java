package ru.kifor4ik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.dto.NewRegistration;
import ru.kifor4ik.dto.RegistrationVisitDto;
import ru.kifor4ik.dto.Visit;
import ru.kifor4ik.service.VisitService;

import java.util.List;

@RestController
@RequestMapping("api/v3/visit")
public class VisitController {

    private VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("/new")
    public void createRegistration(@RequestBody NewRegistration registration){
        visitService.createRegistration(registration.getDate(), registration.getCardNumber(), registration.getDoctor());
    }

    @PostMapping("/newVisit")
    public void createVisit(@RequestBody Visit visit){
        visitService.createVisit(visit);
    }

    @GetMapping
    public RegistrationVisitDto get(@RequestParam Long id){
        return visitService.getById(id);
    }

    @GetMapping("/list")
    public List<RegistrationVisitDto> getAll(){
        return visitService.getAll();
    }
}
