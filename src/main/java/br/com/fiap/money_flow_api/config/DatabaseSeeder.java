package br.com.fiap.money_flow_api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.money_flow_api.model.Category;
import br.com.fiap.money_flow_api.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        var categories = List.of(
                Category.builder().name("Educação").icon("Book").build(),
                Category.builder().name("Transporte").icon("Bus").build(),
                Category.builder().name("Saúde").icon("Heart").build(),
                Category.builder().name("Moradia").icon("House").build());

        categoryRepository.saveAll(categories);
    }

}
