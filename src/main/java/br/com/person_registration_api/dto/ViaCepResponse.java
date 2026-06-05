package br.com.person_registration_api.dto;

import lombok.Data;

@Data
public class ViaCepResponse {

    private String cep;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;
}