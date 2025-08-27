package com.gabrielsa.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.gabrielsa.enums.StatusArtigo;

public record ArtigoDTO(StatusArtigo statusArtigo, String titulo,
        String conteudo, LocalDateTime dataPublicacao,
        Long autorId, List<Long> etiquestasIds) {
}
