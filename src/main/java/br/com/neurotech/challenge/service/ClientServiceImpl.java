package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.repository.NeurotechClientRepository;
import br.com.neurotech.challenge.validation.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final NeurotechClientRepository repository;

    @Override
    public String save(NeurotechClient client) {
        NeurotechClient objClient = repository.save(client);
        Long clientId = Long.valueOf(objClient.getId());
        return String.valueOf(clientId);
    }

    @Override
    public NeurotechClient get(String id) {
        var obj = repository.findById(Long.valueOf(id)).orElseThrow(() -> new ObjectNotFoundException(id));
        return obj;
    }
}
