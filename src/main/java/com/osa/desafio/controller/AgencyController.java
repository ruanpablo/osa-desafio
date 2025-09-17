package com.osa.desafio.controller;

import com.osa.desafio.core.agency.request.FindNearestAgencyRequest;
import com.osa.desafio.core.agency.response.FindNearestAgencyResponse;
import com.osa.desafio.core.agency.request.CreateAgencyRequest;
import com.osa.desafio.core.agency.response.CreateAgencyResponse;
import com.osa.desafio.core.agency.response.PaginationResponse;
import com.osa.desafio.usecase.agency.CreateAgencyUseCase;
import com.osa.desafio.usecase.agency.FindAgencyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "Agências", description = "Operações relacionadas a Agências")
public class AgencyController {
    final CreateAgencyUseCase createAgencyUseCase;
    final FindAgencyUseCase findAgencyUseCase;

    @PostMapping(value = "/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Cadastrar agência",
            description = "Cria uma nova agência com os dados informados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agência criada com sucesso",
                    content = @Content(schema = @Schema(implementation = CreateAgencyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    ResponseEntity<CreateAgencyResponse> createAgency(@RequestBody @Valid CreateAgencyRequest createAgencyRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createAgencyUseCase.createAgency(createAgencyRequest));
    }


    @GetMapping(value = "/distancia", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Buscar agência mais próxima",
            description = "Retorna as agências mais próximas da posição informada pelo usuário."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agência encontrada com sucesso",
                    content = @Content(schema = @Schema(implementation = FindNearestAgencyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    ResponseEntity<PaginationResponse<FindNearestAgencyResponse>> findDistanceAgencyToUser(
            @Parameter(description = "Latitude do usuário", example = "-23")
            @RequestParam(name = "posX") Long latitude,
            @Parameter(description = "Longitude do usuário", example = "-46")
            @RequestParam(name = "posY") Long longitude,
            @Parameter(description = "Número da Página")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Número de agências mais próximas")
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(findAgencyUseCase.findNearestAgencies(new FindNearestAgencyRequest(latitude, longitude, page, size)));
    }

}
