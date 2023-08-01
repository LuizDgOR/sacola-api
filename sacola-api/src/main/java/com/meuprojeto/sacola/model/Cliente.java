package com.meuprojeto.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor // criar os contrutores
@Builder//cria objetos de forma simples
@Data //contem todos os gatters e setters
@Entity // vai ser convertida em uma tabela de BD
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @Embedded // pega o endereço e coloca em cliente , reaproveitando código

    private Endereco endereco;

}
