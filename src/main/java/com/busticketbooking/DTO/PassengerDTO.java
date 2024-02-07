package com.busticketbooking.DTO;

import lombok.Data;

@Data
public class PassengerDTO {
    private int passengerId;
    private String name;
    private String surname;
    private int age;
    private double discount;
}
