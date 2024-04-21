package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.repository.NeurotechClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService{

    private final NeurotechClientRepository clientRepository;

    private static final double TAXA_JUROS_FIXOS = 0.05; // 5% a.a

    @Override
    public boolean checkCredit(String clientId, VehicleModel model) {
        // Obter a idade e a renda do cliente a partir do clientId
        int idadeCliente = getClientAge(clientId);
        double rendaCliente = getClientIncome(clientId);

        // Verificar se o cliente está apto para o crédito automotivo com base no modelo de veículo
        if (model == VehicleModel.HATCH) {
            return isClienteAptoParaCreditoHatch(idadeCliente, rendaCliente);
        } else if (model == VehicleModel.SUV) {
            return isClienteAptoParaCreditoSUV(idadeCliente, rendaCliente);
        } else {
            // Se o modelo de veículo não for reconhecido, consideramos que o cliente não está apto
            return false;
        }
    }

    // Método privado para verificar se o cliente está apto para crédito automotivo para o modelo Hatch
    private boolean isClienteAptoParaCreditoHatch(int idadeCliente, double rendaCliente) {
        // Verificar se o cliente atende aos critérios de idade e renda para crédito com juros fixos
        if (idadeCliente >= 18 && idadeCliente <= 25) {
            double jurosFixos = calcularJurosFixos(idadeCliente);
            return true; // Supondo que o cliente está apto para crédito com juros fixos
        }
        return false;
    }

    // Método privado para verificar se o cliente está apto para crédito automotivo para o modelo SUV
    private boolean isClienteAptoParaCreditoSUV(int idadeCliente, double rendaCliente) {
        if (idadeCliente >= 21 && idadeCliente <= 65 && rendaCliente >= 5000.00 && rendaCliente <= 15000.00) {
            double jurosVariaveis = calcularJurosVariaveis(idadeCliente, rendaCliente);
            return true;
        } else if (idadeCliente >= 65){
            double jurosConseguinado = calcularCreditoConsignado(idadeCliente);
            return true;
        }
        return false;
    }

    private double calcularJurosFixos(int idadeCliente) {
        if (idadeCliente >= 18 && idadeCliente <= 25) {
            return TAXA_JUROS_FIXOS;
        } else {
            return 0.0;
        }
    }

    private double calcularJurosVariaveis( int idadeCliente, double rendaCliente) {
        if (idadeCliente >= 21 && idadeCliente <= 65 && rendaCliente >= 5000.00 && rendaCliente <= 15000.00) {
            return 0.0;
        } else {
            return 0.0;
        }
    }

    private double calcularCreditoConsignado(int idadeCliente) {
        if (idadeCliente > 65) {
            return idadeCliente;
        } else {
            return 0.0;
        }
    }

    // Método para obter a idade real do cliente com base no clientId
    private int getClientAge(String clientId) {
        Optional<NeurotechClient> clientOptional = clientRepository.findById(Long.valueOf(clientId));
        return clientOptional.map(NeurotechClient::getAge).orElse(0);
        // Se o cliente não for encontrado, retorna 0
    }

    // Método para obter a renda real do cliente com base no clientId
    private double getClientIncome(String clientId) {
        Optional<NeurotechClient> clientOptional = clientRepository.findById(Long.valueOf(clientId));
        return clientOptional.map(NeurotechClient::getIncome).orElse(0.0);
        // Se o cliente não for encontrado, retorna 0.0
    }

}
