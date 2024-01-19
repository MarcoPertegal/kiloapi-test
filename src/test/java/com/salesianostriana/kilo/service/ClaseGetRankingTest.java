package com.salesianostriana.kilo.service;

import com.salesianostriana.kilo.KiloApplication;
import com.salesianostriana.kilo.dtos.ranking.RankingResponseDTO;
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

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = KiloApplication.class)
@WebAppConfiguration
public class ClaseGetRankingTest {

    @MockBean
    protected ClaseRepository repository;

    @MockBean
    protected ClaseService service;

    private MockMvc mockMvc;

    @AfterAll
    static void initClase() {
        Clase c1 = Clase.builder()
                .id(1L)
                .nombre("Mates")
                .tutor("Luismi")
                .build();
    }

    @Test
    void getRanking() {
        List<RankingResponseDTO> result = service.getRanking();

        Assertions.assertTrue(result == null);
    }
}
