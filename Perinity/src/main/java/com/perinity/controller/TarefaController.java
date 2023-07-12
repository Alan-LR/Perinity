package com.perinity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perinity.models.Pessoa;
import com.perinity.models.Tarefa;
import com.perinity.repository.PessoaRepository;
import com.perinity.repository.TarefasRepository;
import com.perinity.service.TarefaService;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {

	@Autowired
	private TarefaService tarefasService;

	@Autowired
	private TarefasRepository tarefasRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	// Adicionar um tarefa (post/tarefas)
	@PostMapping(value = "")
	public ResponseEntity<Tarefa> salvarTarefa(@RequestBody Tarefa tarefa) {
		return new ResponseEntity<Tarefa>(tarefasService.salvarTarefa(tarefa), HttpStatus.CREATED);
	}

	// Alocar uma pessoa na tarefa que tenha o mesmo departamento
	// (put/tarefas/alocar/{id})
	@PutMapping(value = "/alocar/{id}")
	public ResponseEntity<Tarefa> alocarTarefa(@PathVariable Integer id, @RequestBody Tarefa tarefaAtt) {
		Tarefa tarefa = tarefasRepository.getReferenceById(id);
		Pessoa pessoa = pessoaRepository.getReferenceById(tarefaAtt.getPessoa().getId());
		if (tarefa.getDepartamento().getId() == pessoa.getDepartamento().getId()) {
			tarefa.setPessoa(tarefaAtt.getPessoa());
			Tarefa tarefaAlocada = tarefasRepository.saveAndFlush(tarefa);
			return new ResponseEntity<>(tarefaAlocada, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// Finalizar a tarefa (put/tarefas/finalizar/{id})
	@PutMapping(value = "/finalizar/{id}")
	public ResponseEntity<Tarefa> finalizarTarefa(@PathVariable Integer id) {
		return new ResponseEntity<Tarefa>(tarefasService.finalizarTarefa(id), HttpStatus.OK);
	}

	// Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos.
	// (get/tarefas/pendentes)
	@GetMapping(value = "/pendentes")
	public ResponseEntity<List<Tarefa>> listarPessoas() {
		return new ResponseEntity<List<Tarefa>>(tarefasService.buscarTarefasAntigaSemPessoa(), HttpStatus.OK);
	}

}
