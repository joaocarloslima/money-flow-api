package br.com.fiap.money_flow_api.service;

import java.time.LocalDate;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.money_flow_api.model.TransactionType;
import br.com.fiap.money_flow_api.repository.TransactionRepository;

@Service
public class AiAnalistyService {

    private final ChatClient chatClient;
    private TransactionRepository transactionRepository;

    public AiAnalistyService(ChatClient.Builder chatClientBuilder, Resource systemMessage, TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

        this.chatClient = chatClientBuilder
                                .defaultSystem(systemMessage)
                                .defaultOptions(ChatOptions.builder()
                                    .temperature(0.9)
                                    .topP(0.7)
                                    .build()
                                )
                                .build();
    }

    public String getAnalise(String subject, String language){
        var expenses = transactionRepository.findByTypeAndDateBetween(TransactionType.EXPENSE, LocalDate.now().withDayOfMonth(1), LocalDate.now());

        var objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(expenses);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return this.chatClient.prompt()
                        .user("faça uma analise das minhas despesas que estão no json: " + json)
                        .system(sp -> sp.param("language", language))
                        .call()
                        .content();
    }
    
}
