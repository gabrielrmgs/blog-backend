package com.gabrielsa.services;

import java.util.List;

import com.gabrielsa.models.Etiqueta;
import com.gabrielsa.repositorys.EtiquetaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EtiquetaService {

    @Inject
    private EtiquetaRepository etiquetaRepository;

    public List<Etiqueta> buscarEtiquetasPorIds(List<Long> idsEtiquetas){
        return etiquetaRepository.list("id in ?1", idsEtiquetas);
    };
    
}
