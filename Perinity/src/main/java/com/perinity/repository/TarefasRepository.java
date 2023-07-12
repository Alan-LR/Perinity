package com.perinity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.perinity.models.Tarefa;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefa, Integer> {

	@Query("SELECT t FROM Tarefa t LEFT JOIN t.pessoa p WHERE p.id IS NULL ORDER BY t.prazo")
	List<Tarefa> buscarTarefasAntigaSemPessoa(Pageable pageable);

}
