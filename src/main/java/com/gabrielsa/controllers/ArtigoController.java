package com.gabrielsa.controllers;

import com.gabrielsa.dtos.ArtigoDTO;
import com.gabrielsa.dtos.ArtigoRespostaDTO;
import com.gabrielsa.models.Artigo;
import com.gabrielsa.services.ArtigoService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/artigo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ArtigoController {

    @Inject
    private ArtigoService artigoService;

    @POST
    @Path("/publicar")
    public Response criarArtigo(ArtigoDTO artigoDTO) {
        try {
            Artigo novoArtigo = artigoService.criarArtigo(artigoDTO);
            return Response.status(Response.Status.CREATED).entity(ArtigoRespostaDTO.fromEntidade(novoArtigo)).build();
        } catch (BadRequestException bre) {
            return Response.status(Response.Status.BAD_REQUEST).entity(bre.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
