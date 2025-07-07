package com.gabrielsa.services;

import java.util.Optional;

import com.gabrielsa.models.Usuario;
import com.gabrielsa.repositorys.UsuarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Objects;

@ApplicationScoped
public class UsuarioService {
    @Inject
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        usuarioRepository.persist(usuario);
        return usuario;
    }

    public Optional<Usuario> buscarNome(String nome) {
        return usuarioRepository.buscarNome(nome);
    }

    public UsuarioRepository getUsuarioRepository() {
        return this.usuarioRepository;
    }
    
}
