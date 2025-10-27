package com.gabrielsa.services;

import java.util.Optional;

import com.gabrielsa.dtos.UsuarioDTO;
import com.gabrielsa.models.Cargo;
import com.gabrielsa.models.Usuario;
import com.gabrielsa.repositorys.CargoRepository;
import com.gabrielsa.repositorys.UsuarioRepository;
import com.gabrielsa.utils.PasswordHasher;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
public class UsuarioService {
    @Inject
    private UsuarioRepository usuarioRepository;
    @Inject
    private CargoRepository cargoRepository;
    @Inject
    private PasswordHasher passwordHasher;

    @Transactional
    public Usuario salvarUsuario(UsuarioDTO dto) {

        if (dto.nome() == null || dto.nome().trim().isEmpty())
            throw new BadRequestException("O nome não pode ser vazio!");
        if (dto.email() == null || !dto.email().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
            throw new BadRequestException("o e-mail não pode ser vazio!");
        if (dto.senha() == null || dto.senha().trim().isEmpty() || dto.senha().length() <= 6)
            throw new BadRequestException("A senha precisar ter 6 ou mais caracteres");
        if (usuarioRepository.buscarPorNome(dto.nome()).isPresent())
            throw new BadRequestException("O nome informado já existe!");
        if (usuarioRepository.buscarPorEmail(dto.email()).isPresent())
            throw new BadRequestException("O e-mail já está cadastrado!");

        Cargo cargoUsuario = null;
        if (dto.cargoId() != null) {
            cargoUsuario = cargoRepository.findById(dto.cargoId());
        }
        Usuario novoUsuario = Usuario.fromDTO(dto, dto.senha(), cargoUsuario);
        usuarioRepository.persist(novoUsuario);
        return novoUsuario;
    }

    public Optional<Usuario> buscarNome(String nome) {
        return usuarioRepository.buscarPorNome(nome);
    }

    public UsuarioRepository getUsuarioRepository() {
        return this.usuarioRepository;
    }

}
