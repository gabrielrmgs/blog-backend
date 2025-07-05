package com.gabrielsa.repositorys;

import com.gabrielsa.models.Etiqueta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EtiquetaRepository implements PanacheRepository<Etiqueta> {

}
