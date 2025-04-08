package br.com.fiap.money_flow_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obrigatório")
    @Size(min = 3, max = 255, message = "deve ter pelo menos 3 caracteres")
    private String name;

    @NotBlank(message = "campo obrigatório")
    @Pattern(regexp = "^[A-Z].*", message = "deve começar com maiúscula")
    private String icon;

}
