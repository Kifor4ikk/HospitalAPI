package ru.kifor4ik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kifor4ik.dto.DoctorDto;
import ru.kifor4ik.entity.Doctor;
import ru.kifor4ik.exception.NotFoundException;
import ru.kifor4ik.repository.DoctorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void create(Doctor doctor){
        doctorRepository.save(DoctorDto.toDto(doctor));
    }

    public Doctor getById(Long id){
        return DoctorDto.toDoctor(doctorRepository.getById(id));
    }

    public void update(Long id, Doctor doctor){
        Doctor doctorTemp = getById(id);
        if(doctorTemp == null)
            throw new NotFoundException("Not found");
        doctorTemp.getWorkDays().clear();

        for(int i = 0; i < doctor.getWorkDays().size(); i++)
            doctorTemp.getWorkDays().addAll(doctor.getWorkDays());
        doctorTemp.setName(doctor.getName());
        doctorTemp.setSurname(doctor.getSurname());
        doctorTemp.setQuality(doctor.getQuality());
        doctorTemp.setAge(doctor.getAge());
        doctorTemp.setDeleted(doctor.isDeleted());
        doctorRepository.save(DoctorDto.toDto(doctorTemp));
    }
    public void deleteStatus(Long id, boolean isDeleted){
        DoctorDto dto = doctorRepository.getById(id);
        dto.setDeleted(isDeleted);
        doctorRepository.save(dto);
    }
    public List<String> getAllQuality(){
       return doctorRepository.findAll().stream().map(DoctorDto::getQuality).collect(Collectors.toList());
    }

    public List<Doctor> getAllQualityDoctors(String quality){
        return doctorRepository.findByQuality(quality).stream().map(DoctorDto::toDoctor).collect(Collectors.toList());
    }

    public List<Doctor> getByFullName(String name,String surname){
        return doctorRepository.findByNameAndSurname(name,surname).stream().map(DoctorDto::toDoctor).collect(Collectors.toList());
    }

    public List<Doctor> getAll(){
        return doctorRepository.findAll().stream().map(DoctorDto::toDoctor).collect(Collectors.toList());
    }
}
