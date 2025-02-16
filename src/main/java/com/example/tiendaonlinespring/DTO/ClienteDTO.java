package com.example.tiendaonlinespring.DTO;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer id;
    private String name;
    private String surname;
    private String nickname;
    private String password;
    private String phone;
    private String address;
}
