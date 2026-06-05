package br.com.person_registration_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "people")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private LocalDate birthDate;

    private String zipCode;

    private String street;

    private String district;

    private String city;

    private String state;

    private String complement;

    @Column(unique = true)
    private String login;

}
