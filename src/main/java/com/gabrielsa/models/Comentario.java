package com.gabrielsa.models;

import com.gabrielsa.generics.ModeloGenerico;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Comentario extends ModeloGenerico{

    @Id
    @SequenceGenerator(name = "comentario_seq_gen", sequenceName = "comentario_seq")
    @GeneratedValue(generator = "comentario_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String conteudo;
    @ManyToOne
    private Usuario autor;
    @ManyToOne
    private Artigo artigo;

    public Comentario() {
    }

    public Comentario(String conteudo, Usuario autor, Artigo artigo) {
        this.conteudo = conteudo;
        this.autor = autor;
        this.artigo = artigo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Artigo getArtigo() {
        return this.artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }

}
