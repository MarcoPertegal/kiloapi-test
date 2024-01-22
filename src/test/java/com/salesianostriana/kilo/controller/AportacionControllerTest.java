package com.salesianostriana.kilo.controller;

import com.salesianostriana.kilo.controllers.AportacionController;
import com.salesianostriana.kilo.dtos.detalles_aportacion.DetallesAportacionResponseDTO;
import com.salesianostriana.kilo.entities.Aportacion;
import com.salesianostriana.kilo.entities.DetalleAportacion;
import com.salesianostriana.kilo.repositories.AportacionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.hamcrest.Matchers.hasSize;
import static org.testcontainers.shaded.org.hamcrest.Matchers.is;

@WebMvcTest(AportacionController.class)
public class AportacionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;
    @MockBean
    AportacionRepository aportacionRepository;

    @Test
    void getDetallesAportacionTest() throws Exception{
        List<Aportacion> aportacion = List.of(
                Aportacion.builder()
                        .id(1L)
                        .fecha('2022-12-13')
                        .detalleAportaciones(DetallesAportacionResponseDTO)

        );
        Mockito.when(aportacionRepository.getAllDetalleAportacion(1L)).thenReturn(aportacion);

        mockMvc.perform(get("/aportacion/{id}").contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].fecha", is('2022-12-13')));
    }

    /*
    @Test
    void getDetallesAportacionTest() throws Exception{


    }*/


}
