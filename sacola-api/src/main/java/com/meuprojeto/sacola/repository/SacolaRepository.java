package com.meuprojeto.sacola.repository;

import com.meuprojeto.sacola.model.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Jparepository ja possui os metodos de deletar salvar , modificar no banco de dados
public interface SacolaRepository extends JpaRepository<Sacola,Long> {
}
