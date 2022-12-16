package com.salesianostriana.kilo.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.kilo.dtos.TipoAlimentoDTO;
import com.salesianostriana.kilo.entities.TipoAlimento;
import com.salesianostriana.kilo.services.TipoAlimentoService;
import com.salesianostriana.kilo.views.View;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tipoAlimento")
@RequiredArgsConstructor
@Tag(name = "Tipo de alimento", description = "Este es el controlador de los tipos de alimento")
public class TipoAlimentoController {

    private final TipoAlimentoService tipoAlimentoService;

    @Operation(summary = "Obtiene todos los tipos de alimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Se han encontrado tipos de alimento",
            content = {
                    @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TipoAlimentoDTO.class)),
                    examples = {
                            @ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 2,
                                                    "nombre": "Pasta"
                                                },
                                                {
                                                    "id": 3,
                                                    "nombre": "Chocolate"
                                                }
                                            ]
                                            """
                            )
                    })
            }),
            @ApiResponse(responseCode = "404",
            description = "No se han encontrado tipos de alimentos",
            content = @Content)
    })
    @JsonView(View.TipoAlimentoView.AllTipoAlimentoView.class)
    @GetMapping("/")
    public ResponseEntity<List<TipoAlimentoDTO>> getAllTipoAlimento() {
        List<TipoAlimento> lista = tipoAlimentoService.findAll();


        if(lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            List<TipoAlimentoDTO> resultado = lista.stream()
                    .map(ta -> TipoAlimentoDTO.of(ta))
                    .toList();
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        }
    }

    @Operation(summary = "Obtiene un tipo de alimento en base a su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Se ha encontrado el tipo de alimento",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TipoAlimentoDTO.class),
                    examples = {
                            @ExampleObject(
                                    value = """
                                            {
                                                "id": 4,
                                                "nombre": "Pasta",
                                                "kilosDisponibles": 10.0
                                            }
                                            """
                            )
                    })
            }),
            @ApiResponse(responseCode = "404",
            description = "No se ha encontrado el tipo de alimento por el ID",
            content = @Content)
    })
    @JsonView(View.TipoAlimentoView.TipoAlimentoByIdView.class)
    @GetMapping("/{id}")
    public ResponseEntity<TipoAlimentoDTO> getTipoAlimentoById(@PathVariable Long id) {
        Optional<TipoAlimento> resultado = tipoAlimentoService.findById(id);
        if(resultado.isPresent()) {
            return ResponseEntity.of(resultado.map(TipoAlimentoDTO::of));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}