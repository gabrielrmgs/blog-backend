package com.gabrielsa.repositorys;

import java.util.Optional;
import com.gabrielsa.models.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Optional<Usuario> buscarNome(String nome) {
        return find("nome = ?1", nome).firstResultOptional();
    }
}
