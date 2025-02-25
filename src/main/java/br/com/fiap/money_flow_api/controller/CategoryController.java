package br.com.fiap.money_flow_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_flow_api.model.Category;

@RestController //component
public class CategoryController {

    //Listar todas as categorias
    // GET :8080/categories -> 200 ok -> json
    @GetMapping("/categories")
    public List<Category> index(){
        return List.of(
            new Category(1L, "Educação", "book"),
            new Category(2L, "Lazer", "casino"),
            new Category(3L, "Salário", "payments")
        );
    }

    //Cadastrar categorias

    //Apagar categorias
    
}
