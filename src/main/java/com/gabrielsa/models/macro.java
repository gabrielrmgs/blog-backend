package com.gabrielsa.models;

import com.gabrielsa.dtos.MacroDTO;
import com.gabrielsa.dtos.MacroRespostaDTO;
import com.gabrielsa.enums.ModuloEnum;
import com.gabrielsa.generics.ModeloGenerico;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import net.bytebuddy.asm.Advice.This;

@Entity
@Table
public class Macro extends ModeloGenerico {
	@Id
	@SequenceGenerator(name = "macro_seq", sequenceName = "macro_seq")
	@GeneratedValue(generator = "macro_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	private String codigoMacro;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private ModuloEnum modulo;

	public Macro(String codigoMacro, String descricao, ModuloEnum modulo) {
		this.codigoMacro = codigoMacro;
		this.descricao = descricao;
		this.modulo = modulo;
	}

	public Macro() {

	}

	public MacroRespostaDTO toDtoResposta() {
		return new MacroRespostaDTO(this.getId(), this.getCodigoMacro(), this.getDescricao(),
				this.getModulo().getNome());

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoMacro() {
		return codigoMacro;
	}

	public void setCodigoMacro(String codigoMacro) {
		this.codigoMacro = codigoMacro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ModuloEnum getModulo() {
		return modulo;
	}

	public void setModulo(ModuloEnum modulo) {
		this.modulo = modulo;
	}

}
