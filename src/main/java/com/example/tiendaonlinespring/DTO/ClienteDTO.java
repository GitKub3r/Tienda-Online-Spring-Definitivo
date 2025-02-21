package com.example.tiendaonlinespring.DTO;

import com.example.tiendaonlinespring.Modelo.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClienteDTO {
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío ni contener solo espacios")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+$", message = "El nombre solo puede contener letras")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío ni contener solo espacios")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+$",
            message = "El apellido solo puede contener letras")
    private String surname;

    @NotBlank(message = "El nickname no puede estar vacío")
    private String nickname;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, mayúsculas, minúsculas y algún número"
    )
    private String password;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[69]\\d{8}$",
            message = "El teléfono debe tener 9 dígitos y comenzar con 6 o 9")
    private String phone;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String address;

    public Cliente createCliente(ClienteDTO clienteDTO) {
        Cliente c = new Cliente();
        c.setNombre(clienteDTO.getName());
        c.setApellido(clienteDTO.getSurname());
        c.setNickname(clienteDTO.getNickname());
        c.setPassword(clienteDTO.getPassword());
        c.setTelefono(clienteDTO.getPhone());
        c.setDomicilio(clienteDTO.getAddress());

        return c;
    }

    public Cliente createClientWithID(ClienteDTO clienteDTO, int id) {
        Cliente c = new Cliente();
        c.setNombre(clienteDTO.getName());
        c.setApellido(clienteDTO.getSurname());
        c.setNickname(clienteDTO.getNickname());
        c.setPassword(clienteDTO.getPassword());
        c.setTelefono(clienteDTO.getPhone());
        c.setDomicilio(clienteDTO.getAddress());
        c.setId(id);

        return c;
    }
}
