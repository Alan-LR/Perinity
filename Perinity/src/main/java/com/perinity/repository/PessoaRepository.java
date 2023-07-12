package com.perinity.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.perinity.models.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	@Query("SELECT p.nome, d.titulo AS departamento, SUM(t.duracao) AS total_horas " + "FROM Pessoa p "
			+ "JOIN p.departamento d " + "JOIN p.tarefas t " + "GROUP BY p.nome, d.titulo")
	List<Object[]> listarPessoasComTotalHoras();

	@Query("SELECT AVG(t.duracao) FROM Pessoa p JOIN p.tarefas t WHERE p.nome = :nome AND t.prazo >= :dataInicio AND t.prazo <= :dataFim")
	Double calcularMediaHorasGastas(@Param("nome") String nome,
			@Param("dataInicio") @org.springframework.data.jpa.repository.Temporal(TemporalType.DATE) Date dataInicio,
			@Param("dataFim") @org.springframework.data.jpa.repository.Temporal(TemporalType.DATE) Date dataFim);

	List<Pessoa> findByNome(String nome);

}
