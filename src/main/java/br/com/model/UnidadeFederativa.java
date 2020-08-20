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

Objetivo: Classe responsavel pelo modelo de dados de usuario

*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class UnidadeFederativa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private int id;
	
	@Column(nullable = false)
	private String descUf;
	
	@Column(nullable = false)
	private String sigla;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescUf() {
		return descUf;
	}

	public void setDescUf(String descUf) {
		this.descUf = descUf;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public String toString() {
		return descUf + " - " + sigla;
	}
	
	
	
}
