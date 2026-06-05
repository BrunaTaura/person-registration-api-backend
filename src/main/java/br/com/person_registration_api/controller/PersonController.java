package br.com.person_registration_api.controller;

import br.com.person_registration_api.dto.CreatePersonRequest;
import br.com.person_registration_api.dto.PersonResponse;
import br.com.person_registration_api.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse create(
            @RequestBody @Valid CreatePersonRequest request) {

        return personService.create(request);
    }
}