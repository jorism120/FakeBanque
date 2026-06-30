package fr.mns.accountservice.adapter.out.http;

import fr.mns.accountservice.domain.port.out.TransactionRecorderPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Component
public class TransactionServiceAdapterOut implements TransactionRecorderPort {

    private final RestTemplate restTemplate;
    private final String transactionServiceUrl;

    public TransactionServiceAdapterOut(RestTemplate restTemplate,
                                        @Value("${transaction.service.url}") String transactionServiceUrl) {
        this.restTemplate = restTemplate;
        this.transactionServiceUrl = transactionServiceUrl;
    }

    /**
     * Appelle le transaction-service pour enregistrer une opération sur un compte.
     */
    @Override
    public void record(String iban, String type, double amount) {
        Map<String, Object> body = Map.of("iban", iban, "type", type, "amount", amount);
        restTemplate.postForEntity(transactionServiceUrl + "/api/transactions", body, Void.class);
    }
}
