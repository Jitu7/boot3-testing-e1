package com.example.boot3testinge1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PersonServiceParameterizedTests {
    PersonService personService;

    @BeforeEach
    void setUp() {
        PersonRepository repo = new PersonRepository();
        personService = new PersonService(repo);
    }

    @ParameterizedTest
    @CsvSource({
            "Jeetu1,jeetu1@gmail.com",
            "Jeetu2,jeetu2@gmail.com",
            "Jeetu3,jeetu3@gmail.com",
    })
    void shouldCreatePersonUsingCSVSuccessfully(String name, String email) {
        Person person = personService.create(new Person(null, name, email));
        assertNotNull(person.getId());
        assertEquals(name, person.getName());
        assertEquals(email, person.getEmail());
    }

    @ParameterizedTest
    @MethodSource("personPropsProvider")
    void shouldCreatePersonSuccessfully(String name, String email) {
        Person person = personService.create(new Person(null, name, email));
        assertNotNull(person.getId());
        assertEquals(name, person.getName());
        assertEquals(email, person.getEmail());
    }

    static Stream<Arguments> personPropsProvider() {
        return Stream.of(
                arguments("Jeetu", "jeetu@gmail.com"),
                arguments("Mahadev", "mahadev@gmail.com")
        );
    }

    @ParameterizedTest
    @MethodSource("personObjectsProvider")
    void shouldCreatePersonWithObjectInputSuccessfully(Person personInput) {
        Person person = personService.create(personInput);
        assertNotNull(person.getId());
        assertEquals(personInput.getName(), person.getName());
        assertEquals(personInput.getEmail(), person.getEmail());
    }

    static Stream<Arguments> personObjectsProvider() {
        return Stream.of(
                arguments(new Person(null, "Suhant", "suhant@gmail.com")),
                arguments(new Person(null, "Asish", "asish@gmail.com"))
        );
    }
}