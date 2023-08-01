package com.meuprojeto.sacola.resource.Dto;


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
//esse pacote pode ser chamado de controller
public class ItemDto {
    private Long produtoId;
    private int quantidade;
    private Long SacolaId;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


}
