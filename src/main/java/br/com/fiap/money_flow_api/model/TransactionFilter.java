package br.com.fiap.money_flow_api.model;

import java.time.LocalDate;

public record TransactionFilter(
        String description,
        LocalDate startDate,
        LocalDate endDate) {
}
