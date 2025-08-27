package com.gabrielsa.repositorys;

import com.gabrielsa.models.Artigo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtigoRepository implements PanacheRepository<Artigo> {

}
