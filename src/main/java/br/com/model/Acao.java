package br.com.model;

/*
SENAI
PSIN
MI-66
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
public class Acao {

	// Atributos da classe
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private int id;
	@Column(nullable = false)
	private boolean entregue;
	@Column(nullable = false)
	private java.sql.Timestamp dataRetirada;
	private java.sql.Timestamp dataEntrega;
	@OneToOne
	private Produto produto;
	@OneToOne
	private Pessoa pessoa;
	
	// Metodos dos dados de ações
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public boolean isEntregue() {
		return entregue;
	}

	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}

	public java.sql.Timestamp getDataRetirada() {
		return dataRetirada;
	}
	
	public void setDataRetirada(java.sql.Timestamp dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
   
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public java.sql.Timestamp getDataEntrega() {
		return dataEntrega;
	}
	
	public void setDataEntrega(java.sql.Timestamp dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	
	/* 
     * Retorno: String
     * Objetivo: Retorna os dados de ações formatados
     * Parametro de Saida:
     *       Acoes
     *
    */
	
	@Override
	public String toString() {
		return "Acao [id=" + id + ", entregue=" + entregue + ", dataRetirada=" + dataRetirada + ", produto=" + produto
				+ ", pessoa=" + pessoa + "]";
	}
	
	
	
}