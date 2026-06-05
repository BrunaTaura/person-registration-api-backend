package br.com.person_registration_api.client;

import br.com.person_registration_api.dto.ViaCepResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ViaCepClient {

    private final RestClient restClient;

    public ViaCepClient() {
        this.restClient = RestClient.create();
    }

    public ViaCepResponse getAddressByZipCode(String zipCode) {

        return restClient.get()
                .uri(
                        "https://viacep.com.br/ws/{zipCode}/json/",
                        zipCode
                )
                .retrieve()
                .body(ViaCepResponse.class);
    }
}