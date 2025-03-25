package br.com.fiap.money_flow_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.money_flow_api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
