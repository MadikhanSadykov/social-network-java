package com.madikhan.app.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String confirmPassword;

}
