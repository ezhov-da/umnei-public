package ru.ezhov.umnei.mathematics.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MathematicsControllerIT {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void generate() throws Exception {
        String url = UriComponentsBuilder.fromUriString("http://localhost:" + port + "/tasks/generate?type=radioNumberEscaped&actions=addition,subtraction,multiplication,division").build().toUri().toURL().toString();

        ResponseEntity<String> response = restTemplate
                .getForEntity(
                        url,
                        String.class
                );

        System.out.println(response.getBody());
    }

}