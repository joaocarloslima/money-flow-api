package br.com.fiap.money_flow_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.money_flow_api.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
}
