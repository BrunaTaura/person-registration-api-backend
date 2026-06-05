package br.com.person_registration_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.person_registration_api.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

}
