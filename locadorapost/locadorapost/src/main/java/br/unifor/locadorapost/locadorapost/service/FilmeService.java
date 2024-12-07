package br.unifor.locadorapost.locadorapost.service;

import br.unifor.locadorapost.locadorapost.model.Filme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FilmeService {
    private List<Filme> filmes;

    public FilmeService() {
        this.filmes = new ArrayList<>();
    }

    public void adicionarFilme(Filme filme) {
        filmes.add(filme);
    }

    public Filme buscarFilme(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            return null;
        }
        return filmes.stream()
                .filter(f -> f.getTitulo().equals(titulo))
                .findFirst().orElse(null);
    }    

    public List<Filme> getFilmes() {
        return filmes;
    }
}

