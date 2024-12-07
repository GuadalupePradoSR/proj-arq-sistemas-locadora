package br.unifor.locadorapost.locadorapost.model;

import java.time.LocalDate;

public class Locacao {
    private Cliente cliente;
    private Filme filme;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;

    public Locacao(Cliente cliente, Filme filme, LocalDate dataLocacao) {
        this.cliente = cliente;
        this.filme = filme;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = null;
    }

    // Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public Filme getFilme() {
        return filme;
    }


    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
