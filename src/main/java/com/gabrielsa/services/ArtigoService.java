package com.gabrielsa.services;

import com.gabrielsa.models.Artigo;
import com.gabrielsa.repositorys.ArtigoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ArtigoService {

    @Inject
    private ArtigoRepository artigoRepository;

    public Artigo criarArtigo(Artigo artigo) {
        artigoRepository.persist(artigo);
        return artigo;
    }

}
