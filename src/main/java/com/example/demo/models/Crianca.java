package com.example.demo.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "crianca")
public class Crianca {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_crianca")
    private Long idCrianca;

    @Column (name = "nome")
    private String nome;

    @Column (name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column (name = "cpf")
    private String cpf;

    @ManyToOne 
    @JoinColumn (name = "id_responsavel")
    private Responsavel responsavel;

    public Crianca() {
    }

    public Long getIdCrianca() {
        return idCrianca;
    }

    public void setIdCrianca(Long idCrianca) {
        this.idCrianca = idCrianca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

}
