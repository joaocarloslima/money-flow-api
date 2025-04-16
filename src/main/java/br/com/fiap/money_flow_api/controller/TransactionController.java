package br.com.fiap.money_flow_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_flow_api.model.Transaction;
import br.com.fiap.money_flow_api.model.TransactionFilter;
import br.com.fiap.money_flow_api.repository.TransactionRepository;
import br.com.fiap.money_flow_api.specification.TransactionSpecification;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public Page<Transaction> index(
            TransactionFilter filter,
            @PageableDefault(size = 10, sort = "date", direction = Direction.DESC) Pageable pageable) {

        var specification = TransactionSpecification.withFilters(filter);
        return repository.findAll(specification, pageable);
    }

}
