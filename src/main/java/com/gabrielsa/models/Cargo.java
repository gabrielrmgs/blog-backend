package com.gabrielsa.models;

import com.gabrielsa.generics.ModeloGenerico;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

public class Cargo extends ModeloGenerico {

    @Id
    @SequenceGenerator(name = "cargo_seq_gen", sequenceName = "cargo_seq")
    @GeneratedValue(generator = "cargo_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;

    public Cargo() {
    }

    public Cargo(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }
    
}
