package com.meuprojeto.sacola.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@AllArgsConstructor // criar os contrutores
@Builder//cria objetos de forma simples
@Data //contem todos os gatters e setters
@NoArgsConstructor
@Embeddable//não vai ser uma tabela do banco de dados.vai colocar esse endereco na classe restaurante
public class Endereco {
    private String cep;
    private String complemento;

}
