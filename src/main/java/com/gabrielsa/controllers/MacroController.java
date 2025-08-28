package com.gabrielsa.controllers;

import com.gabrielsa.dtos.MacroDTO;
import com.gabrielsa.dtos.MacroRespostaDTO;
import com.gabrielsa.models.Macro;
import com.gabrielsa.services.MacroService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path(value = "/macro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class MacroController {

	@Inject
	private MacroService macroService;

	@POST
	@Path("/cadastro")
	@Transactional
	public Response salvarMacro(MacroDTO macroDTO) {

		try {
			Macro novaMacro = macroService.salvarMacro(macroDTO);
			return Response.status(Status.CREATED).entity(novaMacro.toDtoResposta()).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Erro ao criar nova macro = " + e.getMessage()).build();
		}
	}

	public Response atualizarMacro(MacroRespostaDTO macroDTO) {
		try {
			Macro macroAtualizada = macroService.atualizarMacro(macroDTO);
			return Response.status(Status.OK).entity(macroAtualizada.toDtoResposta()).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Erro ao atualizar macro: " + e.getMessage())
					.build();
		}
	}

}
