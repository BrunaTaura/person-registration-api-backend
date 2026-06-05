package br.com.person_registration_api.service;

import br.com.person_registration_api.client.ViaCepClient;
import br.com.person_registration_api.dto.ViaCepResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final ViaCepClient viaCepClient;

    public ViaCepResponse getAddressByZipCode(String zipCode) {

        return viaCepClient.getAddressByZipCode(zipCode);
    }
}