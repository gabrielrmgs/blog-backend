package com.gabrielsa.controllers;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import com.gabrielsa.dtos.LoginDTO;
import com.gabrielsa.models.Usuario;
import com.gabrielsa.repositorys.UsuarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll; // Importante!
import jakarta.annotation.security.RolesAllowed;

@Path("/auth")
@ApplicationScoped
public class AutenticacaoController {
    @Inject
    private UsuarioRepository usuarioRepository;

    /**
     * Endpoint público para login.
     * Recebe username/password, valida, e retorna um JWT.
     */
    @POST
    @Path("/login")
    @PermitAll // Este endpoint é público, não precisa de token
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN) // Retorna o token como texto puro
    public Response login(LoginDTO request) {

        // 1. Encontrar o usuário no banco
        Usuario usuario = usuarioRepository.find("nome", request.nomeUsuario()).firstResult();

        // 2. Validar a senha
        if (usuario != null && BcryptUtil.matches(request.textoSenha(), usuario.getSenha())) {
            // 3. Senha correta! Gerar o token JWT.
            Set<String> roles = new HashSet<>();
            roles.add(usuario.getRole()); // Adiciona o papel (role) do usuário

            String token = Jwt.issuer("https://gemsbiotec.com.br/issuer") // O mesmo issuer do properties
                    .upn(usuario.getNome()) // User Principal Name
                    .groups(roles) // Papéis (claims "groups")
                    .expiresIn(Duration.ofHours(24)) // Validade do token
                    .sign(); // Assina com a chave privada

            return Response.ok(token).build();
        }

        // 4. Usuário ou senha inválidos
        return Response.status(Status.UNAUTHORIZED).entity("Usuário ou senha inválidos").build();
    }

    // (Opcional) Endpoint de teste para ver quem está logado
    @Inject
    SecurityIdentity identity;

    @GET
    @Path("/me")
    @RolesAllowed("user") // Só "user" ou "admin" podem acessar
    @Produces(MediaType.TEXT_PLAIN)
    public String me() {
        return identity.getPrincipal().getName(); // Retorna o username do logado
    }

    @GET
    @Path("/me-debug")
    @Authenticated // <-- Note: @Authenticated, não @RolesAllowed
    @Produces(MediaType.TEXT_PLAIN)
    public String meDebug() {
        // Vamos perguntar ao Quarkus quais papéis ele acha que você tem
        String roles = identity.getRoles().toString();
        String principal = identity.getPrincipal().getName();

        return "Principal: " + principal + " | Roles: " + roles;
    }
}
