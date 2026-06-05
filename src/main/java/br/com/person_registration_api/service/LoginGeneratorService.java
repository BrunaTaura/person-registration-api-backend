package br.com.person_registration_api.service;

import br.com.person_registration_api.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginGeneratorService {

    private final PersonRepository personRepository;

    public String generate(String name) {

        String normalizedName = normalize(name);

        List<String> candidates = generateNaturalCandidates(name);

        for (String candidate : candidates) {
            if (!personRepository.existsByLogin(candidate)) {
                return candidate;
            }
        }

        return generateFallbackLogin(normalizedName);
    }

    private List<String> generateNaturalCandidates(String name) {

        String[] parts = normalize(name).split(" ");

        List<String> candidates = new ArrayList<>();

        if (parts.length < 2) {
            return candidates;
        }

        String firstName = parts[0];

        for (int i = 1; i < parts.length; i++) {

            String currentPart = parts[i];

            String login =
                    buildSevenCharacterLogin(firstName, currentPart);

            candidates.add(login);
        }

        return candidates;
    }

    private String buildSevenCharacterLogin(
            String firstName,
            String secondPart) {

        StringBuilder login = new StringBuilder();

        login.append(firstName);

        int remaining = 7 - login.length();

        if (remaining > 0) {

            login.append(
                    secondPart,
                    0,
                    Math.min(remaining, secondPart.length())
            );
        }

        while (login.length() < 7) {
            login.append(firstName.charAt(0));
        }

        return login.substring(0, 7);
    }

    private String generateFallbackLogin(String normalizedName) {

        String compactName =
                normalizedName.replace(" ", "");

        if (compactName.length() < 7) {

            StringBuilder builder =
                    new StringBuilder(compactName);

            while (builder.length() < 7) {
                builder.append(compactName.charAt(0));
            }

            compactName = builder.toString();
        }

        for (int i = 0; i <= compactName.length() - 7; i++) {

            String candidate =
                    compactName.substring(i, i + 7);

            if (!personRepository.existsByLogin(candidate)) {
                return candidate;
            }
        }

        throw new RuntimeException(
                "Unable to generate a unique login");
    }

    private String normalize(String name) {

        String normalized =
                Normalizer.normalize(
                        name,
                        Normalizer.Form.NFD);

        return normalized
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .replaceAll("[^a-z ]", "")
                .trim()
                .replaceAll("\\s+", " ");
    }
}