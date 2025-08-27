package com.gabrielsa.services;

import com.gabrielsa.dtos.MacroDTO;
import com.gabrielsa.dtos.MacroRespostaDTO;
import com.gabrielsa.enums.ModuloEnum;
import com.gabrielsa.models.Macro;
import com.gabrielsa.repositorys.MacroRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
public class MacroService {

	@Inject
	private MacroRepository macroRepository;

	public Macro salvarMacro(MacroDTO macroDTO) {
		if (!macroDTO.codigoMacro().trim().matches("#.*?#"))
			throw new BadRequestException("Código Macro não está dentro do padrão #CODIGO_MACRO#");
		if (macroDTO.descricaoMacro().trim().isEmpty() || macroDTO.descricaoMacro() == null)
			throw new BadRequestException("Por favor, informe a descrição da macro!");
		ModuloEnum moduloEnum;
		if (macroDTO.moduloName().trim().isEmpty() || macroDTO.moduloName() == null
				|| macroDTO.moduloName().equalsIgnoreCase("TODOS")) {
			moduloEnum = ModuloEnum.TODOS;
		} else {
			moduloEnum = ModuloEnum.valueOf(macroDTO.moduloName());
		}
		;

		Macro macro = Macro.macroFromDto(macroDTO, moduloEnum);
		macroRepository.persist(macro);
		return macro;
	}

	public void excluirMacro(Long id) {
		Macro macroBuscar = macroRepository.findById(id);
		if (macroBuscar == null)
			throw new BadRequestException("Macro não encontrada!");
		if (!macroBuscar.getAtivo())
			throw new BadRequestException("Macro já inativa!");

		macroRepository.update("ativo = false where id = ?1", id);
	}

	public Macro atualizarMacro (MacroRespostaDTO dtoAtualizar) {
		if(dtoAtualizar.codigoMacro().trim().isEmpty() || dtoAtualizar.codigoMacro() == null || !dtoAtualizar.codigoMacro().trim().matches("#.*?#")) throw new BadRequestException("O código precisa seguir os padrões!");
		if(dtoAtualizar.descricaoMacro().trim().isEmpty() || dtoAtualizar.descricaoMacro() == null) throw new BadRequestException("Defina uma descrição!");
		if(dtoAtualizar.moduloNome().trim().isEmpty() || dtoAtualizar.moduloNome() == null) throw new BadRequestException("Selecione um módulo para a macro!");
		macroRepository.update("codigoMacro = ?1 and descricao = ?2 and modulo = ?3", dtoAtualizar.codigoMacro(), dtoAtualizar.descricaoMacro(), dtoAtualizar.moduloNome());:w
		Macro macroAtualizada = macroRepository.findById(dtoAtualizar.id());
		return macroAtualizada;
	}

}
