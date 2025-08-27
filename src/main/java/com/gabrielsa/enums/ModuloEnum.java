package com.gabrielsa.enums;

public enum ModuloEnum {
	TODOS("Todos"),
	RTD("Títulos e Documentos"),
	IMOVEIS("Registro de Imóveis"),
	RCPN("Registro Civil de Pessoas Naturais"),
	RCPJ("Registro Civil de Pessoas Jurídicas");

	private String nome;

	ModuloEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
}
