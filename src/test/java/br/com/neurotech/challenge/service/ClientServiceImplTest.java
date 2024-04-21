package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.repository.NeurotechClientRepository;
import br.com.neurotech.challenge.validation.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private NeurotechClientRepository repository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private final String clientId = "1";

    private NeurotechClient client;

    @BeforeEach
    public void setUp() {
        client = new NeurotechClient();
        client.setId(1L);
        client.setName("John Doe");
        client.setAge(30);
        client.setIncome(5000.0);
    }

    @Test
    void whenSaveThenReturnSuccess() {

        // Mock do repositório para retornar um cliente salvo com ID
        NeurotechClient savedClient = new NeurotechClient();
        savedClient.setId(1L);
        when(repository.save(client)).thenReturn(savedClient);

        // Executar o método a ser testado
        String clientId = clientService.save(client);

        // Verificar o resultado
        assertEquals("1", clientId);
        // O ID retornado deve ser "1"
    }

    @Test
    void whenSaveThenReturnError() {

        // Simular exceção ao tentar salvar o cliente
        doThrow(RuntimeException.class).when(repository).save(client);

        // Verificar se uma exceção é lançada ao chamar o método
        assertThrows(RuntimeException.class, () -> {
            clientService.save(client);
        });
    }

    @Test
    void getSuccess() {

        // Mock do repositório para retornar um cliente quando o ID é passado como parâmetro
        when(repository.findById(1L)).thenReturn(Optional.of(client));

        // Executar o método a ser testado
        NeurotechClient retrievedClient = clientService.get(clientId);

        // Verificar se o cliente retornado é o mesmo que o cliente de teste
        assertEquals(client, retrievedClient);
    }

    @Test
    void getError() {
        // Mock do repositório para retornar um Optional vazio quando o ID é passado como parâmetro
        when(repository.findById(2L)).thenReturn(Optional.empty());

        // Verificar se uma exceção é lançada ao chamar o método com um ID inexistente
        assertThrows(ObjectNotFoundException.class, () -> {
            clientService.get("2");
        });
    }
}