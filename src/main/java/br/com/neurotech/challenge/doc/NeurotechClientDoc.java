package br.com.neurotech.challenge.doc;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

public interface NeurotechClientDoc {

    @Operation(summary = "Salva os clientes", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente salvo com sucesso"),
    })
    ResponseEntity salve(@RequestBody NeurotechClient obj, UriComponentsBuilder uriComponentsBuilder);

    @Operation(summary = "Busca um cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma cliente por id."),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity<NeurotechClient> buscarPorId(@PathVariable String id);

    @Operation(summary = "Busca um cliente fazendo todas a validações que foram pedida no desafio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma cliente por id com suas validações."),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity<String> checkCredit(@RequestParam String clientId, @RequestParam VehicleModel vehicleModel);
}
