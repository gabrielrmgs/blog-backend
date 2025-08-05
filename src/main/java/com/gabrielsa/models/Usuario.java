package com.gabrielsa.models;

import com.gabrielsa.dtos.UsuarioDTO;
import com.gabrielsa.dtos.UsuarioRespostaDTO;
import com.gabrielsa.generics.ModeloGenerico;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Objects;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "nome" }),
        @UniqueConstraint(columnNames = { "email" })
})
public class Usuario extends ModeloGenerico {

    @Id
    @SequenceGenerator(name = "usuario_seq_gen", sequenceName = "usuario_seq")
    @GeneratedValue(generator = "usuario_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    @ManyToOne
    private Cargo cargo;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    public Usuario(String nome, String email, String senha, Cargo cargo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


    public Usuario id(Long id) {
        setId(id);
        return this;
    }

    public Usuario nome(String nome) {
        setNome(nome);
        return this;
    }

    public Usuario email(String email) {
        setEmail(email);
        return this;
    }

    public Usuario senha(String senha) {
        setSenha(senha);
        return this;
    }

    public Usuario cargo(Cargo cargo) {
        setCargo(cargo);
        return this;
    }

    public static Usuario fromDTO(UsuarioDTO dto, String senhaEncriptada, Cargo cargoUsuario) {
        return new Usuario()
        .nome(dto.nome())
        .email(dto.email())
        .senha(senhaEncriptada)
        .cargo(cargoUsuario);
    }

    public UsuarioRespostaDTO toDTO() {
        return new UsuarioRespostaDTO(this.id,this.nome, this.email, this.cargo.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(cargo, usuario.cargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, cargo);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", email='" + getEmail() + "'" +
            ", senha='" + getSenha() + "'" +
            ", cargo='" + getCargo() + "'" +
            "}";
    }
}
