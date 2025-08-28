package com.gabrielsa.dtos;

import com.gabrielsa.enums.ModuloEnum;
import com.gabrielsa.models.Macro;

public record MacroDTO(String codigoMacro, String descricaoMacro, String moduloName) {
	public Macro macroFromDTO() {
		ModuloEnum modulo;
		if (moduloName == null || moduloName.trim().isEmpty()
				|| moduloName.equalsIgnoreCase("TODOS")) {
			modulo = ModuloEnum.TODOS;
		} else {
			modulo = ModuloEnum.valueOf(moduloName);
		}
		return new Macro(codigoMacro, descricaoMacro, modulo);

	}
}
