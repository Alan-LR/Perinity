package com.perinity.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perinity.models.Pessoa;
import com.perinity.repository.PessoaRepository;
import com.perinity.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PessoaRepository pessoaRepository;

	// Adicionar um pessoa (post/pessoas)
	@PostMapping(value = "")
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa) {
		return new ResponseEntity<Pessoa>(pessoaService.salvarPessoa(pessoa), HttpStatus.CREATED);
	}

	// Alterar um pessoa (put/pessoas/{id})
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> alterarPessoa(@PathVariable Integer id, @RequestBody Pessoa pessoaAtualizada) {
		return new ResponseEntity<Pessoa>(pessoaService.alterarPessoa(id, pessoaAtualizada), HttpStatus.OK);
	}

	// Remover pessoa (delete/pessoas/{id})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarPessoa(@PathVariable Integer id) {
		pessoaService.deletarPessoa(id);
		return ResponseEntity.noContent().build();
	}

	// Listar pessoas trazendo nome, departamento, total horas gastas nas
	// tarefas.(get/pessoas)
	@GetMapping(value = "")
	public ResponseEntity<List<Object[]>> listarPessoas() {
		return new ResponseEntity<List<Object[]>>(pessoaService.listarPessoas(), HttpStatus.OK);
	}

	// Buscar pessoas por nome e período, retorna média de horas gastas por tarefa.
	// (get/pessoas/gastos)
	// OBS: ROTA GET NO POSTMAN COM OS PARAMETROS, EX:
	// http://localhost:8080/pessoas/gastos?nome=nomeFuncionario&dataInicio=2022-01-01&dataFim=2023-12-31
	@GetMapping("/gastos")
	public Double calcularMediaHorasGastas(@RequestParam("nome") String nome,
			@RequestParam("dataInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim) {
		return pessoaService.calcularMediaHorasGastas(nome, dataInicio, dataFim);
	}



}
