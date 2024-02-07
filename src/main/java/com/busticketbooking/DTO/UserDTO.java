package com.busticketbooking.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private int  userId;
    private String email;
    private String password;
    private String phoneNumber;
}
