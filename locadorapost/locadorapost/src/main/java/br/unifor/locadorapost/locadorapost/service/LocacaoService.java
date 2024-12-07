package br.unifor.locadorapost.locadorapost.service;

import br.unifor.locadorapost.locadorapost.model.Cliente;
import br.unifor.locadorapost.locadorapost.model.Filme;
import br.unifor.locadorapost.locadorapost.model.Locacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class LocacaoService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FilmeService filmeService;

    private List<Locacao> locacoes = new ArrayList<>();

    public LocacaoService() {
    }

    public void realizarLocacao(String documentoCliente, String tituloFilme) {

        Cliente cliente = clienteService.buscarCliente(documentoCliente);
        Filme filme = filmeService.buscarFilme(tituloFilme);

        if (cliente != null && filme != null) {
            if (filme.isDisponivel()) {
                Locacao locacao = new Locacao(cliente, filme, LocalDate.now());
                // Salvar locação em uma lista ou outra estrutura
                filme.setDisponivel(false);
                locacoes.add(locacao);
                System.out.println("Filme '" + tituloFilme + "' locado com sucesso para o cliente '" + cliente.getNome() + "'.");
            } else {
                System.out.println("O filme '" + tituloFilme + "' não está disponível.");
            }
        } else {
            if (cliente == null) {
                System.out.println("Cliente com o documento '" + documentoCliente + "' não encontrado.");
            }
            if (filme == null) {
                System.out.println("Filme com o título '" + tituloFilme + "' não encontrado.");
            }
        }
    }

    public void realizarDevolucao(String documentoCliente, String tituloFilme) {
        Locacao locacao = locacoes.stream()
                .filter(l -> l.getCliente().getDocumento().equals(documentoCliente) &&
                        l.getFilme().getTitulo().equals(tituloFilme) &&
                        l.getDataDevolucao() == null)
                .findFirst().orElse(null);

        if (locacao != null) {
            locacao.setDataDevolucao(LocalDate.now());
            locacao.getFilme().setDisponivel(true);
            System.out.println("Filme '" + tituloFilme + "' devolvido com sucesso por '" + locacao.getCliente().getNome() + "'.");
        } else {
            System.out.println("Não foi possível encontrar a locação para o filme '" + tituloFilme + "' e o cliente '" + documentoCliente + "'.");
        }
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

}
