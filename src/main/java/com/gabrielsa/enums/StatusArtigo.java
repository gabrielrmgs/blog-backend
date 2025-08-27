package com.gabrielsa.enums;

public enum StatusArtigo {
    RASCUNHO("Rascunho"),
    PUBLICADO("Publicado");

    private String nome;

    StatusArtigo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
}
