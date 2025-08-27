package com.gabrielsa.models;

import com.gabrielsa.generics.ModeloGenerico;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table
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

    public Cargo id(Long id) {
        setId(id);
        return this;
    }

    public Cargo nome(String nome) {
        setNome(nome);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cargo)) {
            return false;
        }
        Cargo cargo = (Cargo) o;
        return Objects.equals(id, cargo.id) && Objects.equals(nome, cargo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }
    
}
