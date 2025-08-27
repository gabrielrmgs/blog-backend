package com.gabrielsa.dtos;

import com.gabrielsa.models.Usuario;

public record UsuarioRespostaDTO(Long id, String nome, String email, Long cargoId) {
    public static UsuarioRespostaDTO fromEntidade(Usuario usuario) {
        if (usuario.getCargo() == null) {
            return new UsuarioRespostaDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(),
                    null);
        }
        return new UsuarioRespostaDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(),
                usuario.getCargo().getId());
    }
}
