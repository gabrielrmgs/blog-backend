package com.gabrielsa.repositorys;

import com.gabrielsa.models.Comentario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ComentarioRepository implements PanacheRepository<Comentario> {

}
