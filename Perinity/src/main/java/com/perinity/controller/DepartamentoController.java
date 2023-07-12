package com.perinity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perinity.service.DepartamentoService;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	// Listar departamento e quantidade de pessoas e tarefas (get/departamentos)
	@GetMapping(value = "")
	public ResponseEntity<List<Object[]>> dpQtdPessoasTarefas() {
		return new ResponseEntity<List<Object[]>>(departamentoService.dpQtdPessoasTarefas(), HttpStatus.OK);
	}

}
