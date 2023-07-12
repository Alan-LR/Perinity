package com.perinity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.perinity.models.Tarefa;
import com.perinity.repository.TarefasRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefasRepository tarefasRepository;

	public Tarefa salvarTarefa(Tarefa tarefa) {
		return tarefasRepository.save(tarefa);
	}

	public Tarefa finalizarTarefa(Integer id) {
		Tarefa tarefa = tarefasRepository.getReferenceById(id);
		tarefa.setFinalizado(true);
		return tarefasRepository.saveAndFlush(tarefa);
	}

	public List<Tarefa> buscarTarefasAntigaSemPessoa() {
		Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "prazo"));
		return tarefasRepository.buscarTarefasAntigaSemPessoa(pageable);
	}

}
