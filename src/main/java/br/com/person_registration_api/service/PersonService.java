package br.com.person_registration_api.service;

import br.com.person_registration_api.dto.CreatePersonRequest;
import br.com.person_registration_api.dto.PersonResponse;
import br.com.person_registration_api.dto.ViaCepResponse;
import br.com.person_registration_api.entity.Person;
import br.com.person_registration_api.exception.BusinessException;
import br.com.person_registration_api.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final LoginGeneratorService loginGeneratorService;
    private final AddressService addressService;
    private final CpfValidatorService cpfValidatorService;

    public PersonResponse create(CreatePersonRequest request) {
        if (!cpfValidatorService.isValid(request.getCpf())) {
            throw new BusinessException("CPF inválido");
        }

        if (personRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }

        if (personRepository.existsByCpf(request.getCpf())) {
            throw new BusinessException("CPF já cadastrado");
        }

        String generatedLogin = loginGeneratorService.generate(request.getName());

        ViaCepResponse addressData = addressService.getAddressByZipCode(request.getZipCode());

        Person person = Person.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .zipCode(request.getZipCode())
                .street(addressData.getLogradouro())
                .district(addressData.getBairro())
                .city(addressData.getLocalidade())
                .state(addressData.getUf())
                .complement(request.getComplement())
                .login(generatedLogin)
                .build();

        Person savedPerson = personRepository.save(person);

        return PersonResponse.builder()
                .id(savedPerson.getId())
                .name(savedPerson.getName())
                .email(savedPerson.getEmail())
                .login(savedPerson.getLogin())
                .build();
    }
}