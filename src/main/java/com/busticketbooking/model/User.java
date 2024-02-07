package com.busticketbooking.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  userId;
    @Column(name = "email")
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;

    //private Set<UserRole> roles;
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;
    //Phone number is string, because we can validate it through regular expressions
    /*public static boolean isValidPhoneNumber(String phoneNumber) {
        // Define a regex pattern for a basic phone number format
        String regex = "^[+]?[0-9]{1,4}[-\\s]?[0-9]{1,15}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a matcher object
        Matcher matcher = pattern.matcher(phoneNumber);

        // Check if the phone number matches the pattern
        return matcher.matches();
    }*/

}