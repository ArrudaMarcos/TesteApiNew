package com.example.TesteApiNew.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document(collection = "cliente")
public class Cliente {

    @Id
    private String idCliente;

    @NotNull
    private String nome;

    @NotNull
    private int idade;

    private Historico historico;

    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return idCliente == cliente.idCliente &&
                idade == cliente.idade &&
                nome.equals(cliente.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nome, idade);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente='" + idCliente + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", historico=" + historico +
                '}';
    }
}
