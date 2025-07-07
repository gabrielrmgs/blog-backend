package com.gabrielsa.controllers;

import java.time.LocalDateTime;

import com.gabrielsa.dtos.ArtigoDTO;
import com.gabrielsa.dtos.ArtigoRespostaDTO;
import com.gabrielsa.models.Artigo;
import com.gabrielsa.models.Usuario;
import com.gabrielsa.services.ArtigoService;
import com.gabrielsa.services.UsuarioService;

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
    @Inject
    private UsuarioService usuarioService;

    @POST
    @Path("/publicar")
    public Response criarArtigo(ArtigoDTO artigoDTO) {

        if(artigoDTO.autorId() == null) throw new BadRequestException("Autor é necessário para publicação de um artigo!");
       
        Usuario autorArtigo = usuarioService.getUsuarioRepository().findById(artigoDTO.autorId());
        
        if(autorArtigo == null) throw new BadRequestException("Autor não encontrado!");
        
        Artigo novoArtigo = Artigo.fromDTO(artigoDTO, autorArtigo, null); //ainda falta chamar o service das estiquetas(que não esta
        //feito ainda e pesquisá-las pelos ids recebidos)

        artigoService.criarArtigo(novoArtigo);

        ArtigoRespostaDTO resposta = novoArtigo.toDTO();

        return Response.status(Response.Status.CREATED).entity(resposta).build();
    }
}
