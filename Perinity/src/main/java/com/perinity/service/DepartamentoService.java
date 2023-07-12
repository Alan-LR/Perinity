package com.perinity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perinity.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	public List<Object[]> dpQtdPessoasTarefas() {
		return departamentoRepository.dpQtdPessoasTarefas();
	}
	

}
