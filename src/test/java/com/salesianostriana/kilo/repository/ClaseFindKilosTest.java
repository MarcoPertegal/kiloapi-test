package com.salesianostriana.kilo.repository;

import com.salesianostriana.kilo.KiloApplication;
import com.salesianostriana.kilo.entities.Clase;
import com.salesianostriana.kilo.repositories.ClaseRepository;
import com.salesianostriana.kilo.services.ClaseService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ContextConfiguration(classes = KiloApplication.class)
@WebAppConfiguration
public class ClaseFindKilosTest {

    @MockBean
    protected ClaseRepository repository;

    @MockBean
    protected ClaseService service;

    @AfterAll
    static void initClase() {
        Clase c1 = Clase.builder()
                .id(1L)
                .nombre("Mates")
                .tutor("Luismi")
                .build();
    }

    @Test
    void claseFind() {
        Double result = repository.findKilos(1L);
        Assertions.assertTrue(result == 0);
    }
}
