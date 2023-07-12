package com.perinity.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perinity.models.Pessoa;
import com.perinity.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa salvarPessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public Pessoa alterarPessoa(Integer id, Pessoa pessoaAtualizada) {
		Pessoa pessoa = pessoaRepository.getReferenceById(id);
		pessoa.setNome(pessoaAtualizada.getNome());
		pessoa.setDepartamento(pessoaAtualizada.getDepartamento());
		return pessoaRepository.save(pessoa);
	}

	public void deletarPessoa(Integer id) {
		pessoaRepository.deleteById(id);
	}

	public List<Object[]> listarPessoas() {
		return pessoaRepository.listarPessoasComTotalHoras();
	}

	public Double calcularMediaHorasGastas(String nome, Date dataInicio, Date dataFim) {
		return pessoaRepository.calcularMediaHorasGastas(nome, dataInicio, dataFim);
	}


}
