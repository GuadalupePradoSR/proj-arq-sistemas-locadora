package br.unifor.locadorapost.locadorapost.controller;

import br.unifor.locadorapost.locadorapost.model.*;
import br.unifor.locadorapost.locadorapost.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/locadora")
public class LocadoraController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LocacaoService locacaoService;

    // Endpoint para cadastrar um filme
    @PostMapping("/filmes")
    public String cadastrarFilme(@RequestBody Map<String, String> filmeRequest) {
        try {
            String titulo = filmeRequest.get("titulo");
            String genero = filmeRequest.get("genero");
            String formato = filmeRequest.get("formato");

            Filme filme = criarFilme(titulo, genero, formato);
            filmeService.adicionarFilme(filme);

            return "Filme cadastrado com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    private Filme criarFilme(String titulo, String genero, String formato) {
        if (formato.equalsIgnoreCase("DVD")) {
            return new FilmeDVD(titulo, genero);
        } else if (formato.equalsIgnoreCase("Blu-ray")) {
            return new FilmeBluRay(titulo, genero);
        } else {
            throw new IllegalArgumentException("Formato desconhecido.");
        }
    }

    // Endpoint para cadastrar um cliente
    @PostMapping("/clientes")
    public String cadastrarCliente(@RequestBody Cliente cliente) {
        clienteService.adicionarCliente(cliente);
        return "Cliente cadastrado com sucesso!";
    }

    // Endpoint para realizar uma locação
    @PostMapping("/locacoes")
    public String locarFilme(@RequestBody Map<String, String> request) {
        try {
            String documentoCliente = request.get("documentoCliente");
            String tituloFilme = request.get("tituloFilme");

            locacaoService.realizarLocacao(documentoCliente, tituloFilme);

            return "Locação realizada com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Endpoint para realizar uma devolução
    @PostMapping("/devolucoes")
    public String devolverFilme(@RequestBody Map<String, String> locacaoRequest) {
        try {
            String documentoCliente = locacaoRequest.get("documentoCliente");
            String tituloFilme = locacaoRequest.get("tituloFilme");

            locacaoService.realizarDevolucao(documentoCliente, tituloFilme);

            return "Devolução realizada com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Endpoint para deletar um cliente
    @DeleteMapping("/clientes/{documento}")
    public String deletarCliente(@PathVariable String documento) {
        boolean removido = clienteService.deletarCliente(documento);
        return removido ? "Cliente deletado com sucesso!" : "Cliente não encontrado!";
    }

    // Endpoint para listar todos os filmes
    @GetMapping("/filmes")
    public List<Filme> listarFilmes() {
        return filmeService.getFilmes();
    }

    // Endpoint para listar todos os clientes
    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return clienteService.getClientes();
    }

    // Endpoint para listar todas as locações
    @GetMapping("/locacoes")
    public List<Locacao> listarLocacoes() {
        return locacaoService.getLocacoes();
    }
}
