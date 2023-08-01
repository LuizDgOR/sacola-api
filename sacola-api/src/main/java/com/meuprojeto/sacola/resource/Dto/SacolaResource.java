package com.meuprojeto.sacola.resource.Dto;


import com.meuprojeto.sacola.model.Item;
import com.meuprojeto.sacola.model.Sacola;
import com.meuprojeto.sacola.sevice.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ifood-devweek/sacolas")//define a Url para usar sacola
@RequiredArgsConstructor
public class SacolaResource {

    private final SacolaService sacolaService;


    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto){
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable ("id") Long id){
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/ {sacolaId}") //metodo de atualização da sacola
    public Sacola fecharSacola(@PathVariable ("sacolaId") Long sacolaId,
                               @RequestParam("formaPagamento") int formaPagamento){
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }
}
