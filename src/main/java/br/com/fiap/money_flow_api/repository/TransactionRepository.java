package br.com.fiap.money_flow_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.money_flow_api.model.Transaction;
import br.com.fiap.money_flow_api.model.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    Page<Transaction> findByDescriptionContainingIgnoringCase(String description, Pageable pageable); // Query Method

    List<Transaction> findByTypeAndDateBetween(TransactionType expense, LocalDate withDayOfMonth, LocalDate now);

}
