package com.salesianostriana.kilo.repository;

import com.salesianostriana.kilo.dtos.aportaciones.AportacionesReponseDTO;
import com.salesianostriana.kilo.entities.Aportacion;
import com.salesianostriana.kilo.repositories.AportacionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles({"postgresql"})//seleccionamos el perfil activo
@Testcontainers
@Sql(value = "classpath:import.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:test/insert-aportacion.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:test/delete-aportacion.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AportacionRepositoryTests {
    //TEST realizado por Marco
    /*
    @Query("""
            SELECT new com.salesianostriana.kilo.dtos.aportaciones.AportacionesReponseDTO(a.id, a.fecha, c.nombre, SUM(da.cantidadKg))
            FROM Aportacion a LEFT JOIN Clase c ON (a.clase.id = c.id)
                              JOIN DetalleAportacion da ON (da.aportacion.id = a.id)
            GROUP BY a.id
            """)
    List<AportacionesReponseDTO> getAllAportaciones();
     */
    @Autowired
    AportacionRepository aportacionRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer(DockerImageName.parse("postgres:16-alpine"))
            .withUsername("testUser")
            .withPassword("testSecret")
            .withDatabaseName("testDatabase");
    @Test
    void findByPetId(){
        List<AportacionesReponseDTO> result = aportacionRepository.getAllAportaciones();
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.get(1).getId());
    }
}
