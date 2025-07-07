package com.gabrielsa.controllers;

import com.gabrielsa.models.Usuario;
import com.gabrielsa.repositorys.UsuarioRepository;
import com.gabrielsa.services.UsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

    @POST
    @Path("/cadastro")
    @Transactional
    public Response salvarUsuario(Usuario usuario) {
        Usuario novUsuario = usuarioService.salvarUsuario(usuario);
        return Response.status(Response.Status.CREATED)
                .entity("Usuario " + novUsuario.getNome())
                .build();
    }

    @GET
    @Path("/{nome}")
    public Response buscarUsuario(@PathParam("nome") String nome) {
        try {
            return Response.ok(usuarioService.buscarNome(nome).get().getNome()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("id={id}")
    public Response buscarUsuarioId(@PathParam("id") Long id){
        try{
            Usuario usuarioTeste = usuarioRepository.findById(id);
            if(usuarioTeste != null) {
                return Response.ok(usuarioTeste.getNome() + " Email: " + usuarioTeste.getEmail()).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Nadaaaa").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro "+ e.getMessage()).build();
        }
    }

}
