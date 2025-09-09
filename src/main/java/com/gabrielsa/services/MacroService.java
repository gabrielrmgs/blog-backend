package com.gabrielsa.services;

import java.util.ArrayList;
import java.util.List;

import com.gabrielsa.dtos.MacroDTO;
import com.gabrielsa.dtos.MacroRespostaDTO;
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

		if (macroDTO.codigoMacro() == null || !macroDTO.codigoMacro().trim().matches("#.*?#"))
			throw new BadRequestException("Código Macro não está dentro do padrão #CODIGO_MACRO#");
		if (macroDTO.descricaoMacro() == null || macroDTO.descricaoMacro().trim().isEmpty())
			throw new BadRequestException("Por favor, informe a descrição da macro!");

		Macro macro = macroDTO.macroFromDTO();
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

	public Macro atualizarMacro(MacroRespostaDTO dtoAtualizar) {
		if (dtoAtualizar.codigoMacro().trim().isEmpty() || dtoAtualizar.codigoMacro() == null
				|| !dtoAtualizar.codigoMacro().trim().matches("#.*?#"))
			throw new BadRequestException("O código precisa seguir os padrões!");
		if (dtoAtualizar.descricaoMacro().trim().isEmpty() || dtoAtualizar.descricaoMacro() == null)
			throw new BadRequestException("Defina uma descrição!");
		if (dtoAtualizar.moduloNome().trim().isEmpty() || dtoAtualizar.moduloNome() == null)
			throw new BadRequestException("Selecione um módulo para a macro!");
		macroRepository.update("codigoMacro = ?1 and descricao = ?2 and modulo = ?3",
				dtoAtualizar.codigoMacro(), dtoAtualizar.descricaoMacro(), dtoAtualizar.moduloNome());
		Macro macroAtualizada = macroRepository.findById(dtoAtualizar.id());
		return macroAtualizada;
	}

	public List<Macro> buscarMacros() {
		List<Macro> macros = new ArrayList<>();
		macros = macroRepository.findAll().list();
		return macros;
	}

	public void salvarListaMacros(List<MacroDTO> macrosDTOs) {
		if (!macrosDTOs.isEmpty()) {
			for (MacroDTO dto : macrosDTOs) {
				salvarMacro(dto);
			}
		}
	}

}
