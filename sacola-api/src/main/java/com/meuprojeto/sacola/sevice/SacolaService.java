package com.meuprojeto.sacola.sevice;

import com.meuprojeto.sacola.model.Item;
import com.meuprojeto.sacola.model.Sacola;
import com.meuprojeto.sacola.resource.Dto.ItemDto;

public interface SacolaService {

    Item incluirItemNaSacola(ItemDto itemDto);
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int FormaPagamento);
}
