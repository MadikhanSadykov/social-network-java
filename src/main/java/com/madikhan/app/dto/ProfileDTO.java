package com.madikhan.app.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ProfileDTO {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String bio;
    private Date birthDate;
    private String phoneNumber;
    private Long userId;
}
