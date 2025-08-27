package com.gabrielsa.controllers;

import java.util.Optional;

import com.gabrielsa.dtos.UsuarioDTO;
import com.gabrielsa.dtos.UsuarioRespostaDTO;
import com.gabrielsa.models.Usuario;
import com.gabrielsa.repositorys.UsuarioRepository;
import com.gabrielsa.services.UsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(value = "/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class UsuarioController {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private UsuarioRepository usuarioRepository;
    // private EncriptadorHash encriptadorHash;

    @POST
    @Path("/cadastro")
    @Transactional
    public Response salvarUsuario(UsuarioDTO usuarioDTO) {

        try {
            Usuario novoUsuario = usuarioService.salvarUsuario(usuarioDTO);
            return Response.status(Response.Status.CREATED).entity(UsuarioRespostaDTO.fromEntidade(novoUsuario))
                    .build();
        } catch (BadRequestException bre) {
            return Response.status(Response.Status.BAD_REQUEST).entity(bre.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{nome}")
    public Response buscarUsuario(@PathParam("nome") String nome) {
        try {
            Optional<Usuario> usuarioPesquisa = usuarioService.buscarNome(nome);
            if(!usuarioPesquisa.isPresent()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuário de nome " + nome + " não encontrado!").build();
            }
            UsuarioRespostaDTO resposta = UsuarioRespostaDTO.fromEntidade(usuarioPesquisa.get());
            return Response.ok(resposta).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("id={id}")
    public Response buscarUsuarioId(@PathParam("id") Long id) {
        try {
            Usuario usuarioPesquisa = usuarioRepository.findById(id);
            UsuarioRespostaDTO resposta = UsuarioRespostaDTO.fromEntidade(usuarioPesquisa);
            if (usuarioPesquisa != null) {
                return Response.ok(resposta).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Nadaaaa").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro " + e.getMessage()).build();
        }
    }

}
