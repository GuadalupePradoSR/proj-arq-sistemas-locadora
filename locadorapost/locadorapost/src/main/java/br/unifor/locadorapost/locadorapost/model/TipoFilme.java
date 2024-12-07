package br.unifor.locadorapost.locadorapost.model;

public interface TipoFilme {
    String getFormato();

    double calcularPreco();

    String exibirDetalhes();
}
