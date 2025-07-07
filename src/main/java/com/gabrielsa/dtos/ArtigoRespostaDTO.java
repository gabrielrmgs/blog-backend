package com.gabrielsa.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.gabrielsa.enums.StatusArtigo;

public record ArtigoRespostaDTO(Long id, StatusArtigo statusArtigo,
        String titulo, String conteudo, LocalDateTime dataPublicacao,
        String nomeAutor, List<String> etiquetas) {
}
