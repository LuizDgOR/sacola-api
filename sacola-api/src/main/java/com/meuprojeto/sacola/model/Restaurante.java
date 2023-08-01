package com.meuprojeto.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;




@AllArgsConstructor // criar os contrutores
@Builder//cria objetos de forma simples
@Data //contem todos os gatters e setters
@Entity // vai ser convertida em uma tabela de BD
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //gera valores incrementando os IDs

    private Long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL)//tipo de relacionamento e o cascade é para quando mudar em lugar alterar em outro
    private List<Produto> cardapio;

    @Embedded//
    private Endereco endereco;

}
