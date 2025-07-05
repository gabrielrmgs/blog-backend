package com.gabrielsa.models;

import java.time.LocalDateTime;
import java.util.List;

import com.gabrielsa.enums.StatusArtigo;
import com.gabrielsa.generics.ModeloGenerico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Artigo extends ModeloGenerico {

    @Id
    @SequenceGenerator(name = "artigo_seq_gen", sequenceName = "artigo_seq")
    @GeneratedValue(generator = "artigo_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusArtigo status;

    private String titulo;

    @ManyToOne
    private Usuario autor;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    private LocalDateTime dataPublicacao;

    @ManyToMany
    private List<Etiqueta> etiquetas;

    public Artigo() {
    }

    public Artigo(StatusArtigo status, String titulo, Usuario autor, String conteudo, LocalDateTime dataPublicacao, List<Etiqueta> etiquetas) {
        this.status = status;
        this.titulo = titulo;
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = dataPublicacao;
        this.etiquetas = etiquetas;
    }

    public Artigo(StatusArtigo status, String titulo, Usuario autor, String conteudo, List<Etiqueta> etiquetas) {
        this.status = status;
        this.titulo = titulo;
        this.autor = autor;
        this.conteudo = conteudo;
        this.etiquetas = etiquetas;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusArtigo getStatus() {
        return this.status;
    }

    public void setStatus(StatusArtigo status) {
        this.status = status;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return this.dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Etiqueta> getEtiquetas() {
        return this.etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
