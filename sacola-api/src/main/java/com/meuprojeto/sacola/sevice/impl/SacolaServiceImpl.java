package com.meuprojeto.sacola.sevice.impl;

import com.meuprojeto.sacola.enumeration.FormaPagamento;
import com.meuprojeto.sacola.model.Item;
import com.meuprojeto.sacola.model.Produto;
import com.meuprojeto.sacola.model.Restaurante;
import com.meuprojeto.sacola.model.Sacola;
import com.meuprojeto.sacola.repository.ItemRepository;
import com.meuprojeto.sacola.repository.ProdutoRepository;
import com.meuprojeto.sacola.repository.SacolaRepository;
import com.meuprojeto.sacola.resource.Dto.ItemDto;
import com.meuprojeto.sacola.sevice.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;


    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());

        if(sacola.isFechada()){
            throw new RuntimeException("Essa sacola está fechada");

        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Essa produto não existe!");
                        }
                ))
                .build();



        List<Item> itensDaSAcola = sacola.getItens();
        if(itensDaSAcola.isEmpty()){
            itensDaSAcola.add(itemParaSerInserido);
        }else {
            Restaurante restauranteAtual = itensDaSAcola.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteDoItemParaAdicionar)){
                itensDaSAcola.add(itemParaSerInserido);
            }else{
                throw new RuntimeException("Não é possivel adicionar produtos de restaurante diferentes .Feche a sacola ou esvazie");
            }
        }

        List<Double> valorDosItens = new ArrayList<Double>();
        for (Item itemDaSacola:itensDaSAcola){
            double valorTotalItem =
                    itemDaSacola.getProduto().getValorUnitario()*itemDaSacola.getQuantidade();
            valorTotaldosItens.add(valorTotalItem);
        }

        double valorTotalSacola = valorDosItens.stream()
                        .mapToDouble(valoTotaldeCadaItem -> valoTotaldeCadaItem)
                                .sum();

        sacola.setValorTotal(valorTotalSacola);

        sacolaRepository.save(sacola);
        return itemRepository.save(itemParaSerInserido);

    }


    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                ()-> {
                    throw new RuntimeException("Essa sacola não existe!");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroFormaPagamento) {
        Sacola sacola = verSacola(id);

        if (sacola.getItens().isEmpty()){
            throw new RuntimeException("Inclua itens na sacola!");
        }

        FormaPagamento formaPagamento =
        numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO: FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);

    }
}
