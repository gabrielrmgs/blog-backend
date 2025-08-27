package com.gabrielsa.generics;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public class ModeloGenerico {

	@Column(nullable = false, updatable = false)
	private LocalDateTime dataCriacao;

	@Column(nullable = false)
	private LocalDateTime dataAtualizacao;

	private Boolean ativo;

	public ModeloGenerico() {
	}

	@PrePersist
	protected void onCreate() {
		this.dataCriacao = LocalDateTime.now();
		this.dataAtualizacao = LocalDateTime.now();
		this.ativo = true;
	}

	@PreUpdate
	protected void onUpdate() {
		this.dataAtualizacao = LocalDateTime.now();
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
