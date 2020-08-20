package br.com.model;

/*
SENAI
PSIN
MI-66
Objetivo:
Autores: Leonardo Pio, Kelvin Schneider, Guilherme Witkosky, Rafael Adriano e Vinicius Silva Sena
Data: 06/08/2020

Alterações: 
Nome: Vinícius Sena
Alterou: Documentou o codigo

Objetivo: Classe responsavel pelo modelo de dados de pessoas

*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pessoa {

	// Atributos de pessoas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private int id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cpf;
	@Column(nullable = false)
	private String entrada;
	@Column(nullable = false)
	private String saida;
	@OneToOne
	private Usuario usuario;
	@OneToOne
	private Endereco endereco;
	
	// METODOS BASICOS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	/* 
     * Retorno: String
     * Objetivo: Retorna os dados de pessoa formatados
     * Parametro de Saida:
     *       Acoes
     *
    */
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", entrada=" + entrada + ", saida=" + saida
				+ ", usuario=" + usuario + "]";
	}
	
	
}
