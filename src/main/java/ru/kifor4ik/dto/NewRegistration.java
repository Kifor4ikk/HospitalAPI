package ru.kifor4ik.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class NewRegistration {

    private String cardNumber;
    private Long doctor;
    private Date date;
}
