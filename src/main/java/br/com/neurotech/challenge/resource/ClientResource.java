package br.com.neurotech.challenge.resource;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.ClientService;
import br.com.neurotech.challenge.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClientResource {

    private final ClientService clientService;

    private final CreditService creditService;

    @PostMapping("/client")
    public ResponseEntity salve(@RequestBody NeurotechClient obj, UriComponentsBuilder uriComponentsBuilder){
        clientService.save(obj);
        String uri = uriComponentsBuilder.path("/api/client/{id}").buildAndExpand(obj.getId()).toUriString();
        // Cria o cabeçalho com a URL do recurso
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);
        // Retorna a resposta com o cabeçalho e o status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(obj);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<NeurotechClient> buscarPorId(@PathVariable String id){
        NeurotechClient obj = clientService.get(id.toString());
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/client/check")
    public ResponseEntity<String> checkCredit(@RequestParam String clientId, @RequestParam VehicleModel vehicleModel) {
        boolean isCreditApproved = creditService.checkCredit(clientId, vehicleModel);
        if (isCreditApproved) {
            return ResponseEntity.ok("Crédito aprovado para o cliente " + clientId + " para o modelo " + vehicleModel);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Crédito não aprovado para o cliente " + clientId + " para o modelo " + vehicleModel);
        }
    }
}
