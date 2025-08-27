package com.gabrielsa.models;

import com.gabrielsa.generics.ModeloGenerico;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Etiqueta extends ModeloGenerico {

    @Id
    @SequenceGenerator(name = "etiqueta_seq_gen", sequenceName = "etiqueta_seq")
    @GeneratedValue(generator = "etiqueta_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;

    public Etiqueta() {
    }

    public Etiqueta(String nome) {
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

}
