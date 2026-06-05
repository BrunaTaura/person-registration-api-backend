package br.com.person_registration_api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePersonRequest {

    @NotBlank
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "Name must contain only letters and spaces"
    )
    private String name;

    @NotBlank
    @Pattern(
            regexp = "\\d{11}",
            message = "CPF must contain exactly 11 digits"
    )
    private String cpf;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotBlank
    @Pattern(
            regexp = "\\d{8}",
            message = "Zip code must contain 8 digits"
    )
    private String zipCode;

    private String complement;
}