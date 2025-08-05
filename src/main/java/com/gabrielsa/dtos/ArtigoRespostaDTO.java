package com.gabrielsa.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.gabrielsa.enums.StatusArtigo;
import com.gabrielsa.models.Artigo;

public record ArtigoRespostaDTO(Long id, StatusArtigo statusArtigo,
                String titulo, String conteudo, LocalDateTime dataPublicacao,
                String nomeAutor, List<String> etiquetas) {

        public static ArtigoRespostaDTO fromEntidade(Artigo artigo) {
                return new ArtigoRespostaDTO(
                                artigo.getId(),
                                artigo.getStatus(),
                                artigo.getTitulo(),
                                artigo.getConteudo(),
                                artigo.getDataPublicacao(),
                                artigo.getAutor().getNome(),
                                null);
        }

}
