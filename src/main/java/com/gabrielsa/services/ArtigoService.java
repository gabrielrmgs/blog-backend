package com.gabrielsa.services;

import java.util.List;

import com.gabrielsa.dtos.ArtigoDTO;
import com.gabrielsa.models.Artigo;
import com.gabrielsa.models.Etiqueta;
import com.gabrielsa.models.Usuario;
import com.gabrielsa.repositorys.ArtigoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
public class ArtigoService {

    @Inject
    private ArtigoRepository artigoRepository;
    @Inject
    private UsuarioService usuarioService;
    @Inject
    private EtiquetaService etiquetaService;

    public Artigo criarArtigo(ArtigoDTO artigoDTO) {
        if (artigoDTO.autorId() == null)
            throw new BadRequestException("Autor é obrigatório para publicação do artigo!");
        Usuario autor = usuarioService.getUsuarioRepository().findById(artigoDTO.autorId());
        if (artigoDTO.titulo() == null || artigoDTO.titulo().trim().isEmpty())
            throw new BadRequestException("O título é obrigatório para publicação do artigo!");
        if (artigoDTO.conteudo() == null || artigoDTO.conteudo().trim().isEmpty())
            throw new BadRequestException("O conteúdo da publicação não pode estar vazio!");
        if (artigoDTO.statusArtigo() == null) throw new BadRequestException("Selecione um status para o artigo!");
        if(artigoDTO.etiquestasIds() == null || artigoDTO.etiquestasIds().isEmpty()) throw new BadRequestException("Selecione pelo menos uma tag para a publicação!");

        List<Etiqueta> etiquetas = etiquetaService.buscarEtiquetasPorIds(artigoDTO.etiquestasIds());
        Artigo novoArtigo = Artigo.fromDTO(artigoDTO, autor, etiquetas);
        artigoRepository.persist(novoArtigo);
        return novoArtigo;
    }

}
