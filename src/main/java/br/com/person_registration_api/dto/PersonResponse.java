package br.com.person_registration_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponse {

    private Long id;

    private String name;

    private String email;

    private String login;
}
