package br.com.fiap.money_flow_api.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.money_flow_api.model.Transaction;
import br.com.fiap.money_flow_api.model.TransactionFilter;
import jakarta.persistence.criteria.Predicate;

public class TransactionSpecification {

    public static Specification<Transaction> withFilters(TransactionFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.description() != null) {
                predicates.add(
                        cb.like(root.get("description"), filter.description()));
            }

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(
                        cb.between(root.get("date"), filter.startDate(), filter.endDate()));
            }

            if (filter.startDate() != null && filter.endDate() == null) {
                predicates.add(
                        cb.equal(root.get("date"), filter.startDate()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }

}
