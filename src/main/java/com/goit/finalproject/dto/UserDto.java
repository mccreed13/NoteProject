package com.goit.finalproject.dto;

import lombok.Data;

@Data //TODO added @Data
public class UserDto {
    private Long id;
    private String username; //TODO login was changed on username
    private String password;
}
