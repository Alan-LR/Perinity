package com.perinity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perinity.models.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>{

	@Query("SELECT d.titulo AS departamento, COUNT(p) AS quantidadePessoas, COUNT(t) AS quantidadeTarefas " +
	        "FROM Departamento d " +
	        "LEFT JOIN d.pessoas p " +
	        "LEFT JOIN d.tarefas t " +
	        "GROUP BY d.titulo")
	List<Object[]> dpQtdPessoasTarefas();

}
