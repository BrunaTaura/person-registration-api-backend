package br.com.person_registration_api.service;

import br.com.person_registration_api.client.ViaCepClient;
import br.com.person_registration_api.dto.ViaCepResponse;
import br.com.person_registration_api.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final ViaCepClient viaCepClient;

    public ViaCepResponse getAddressByZipCode(String zipCode) {
        ViaCepResponse response = viaCepClient.getAddressByZipCode(zipCode);

        if (response == null || Boolean.TRUE.equals(response.getErro())) {
            throw new BusinessException("CEP não encontrado");
        }

        return response;
    }
}