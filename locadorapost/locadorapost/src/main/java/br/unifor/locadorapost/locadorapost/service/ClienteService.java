package br.unifor.locadorapost.locadorapost.service;

import br.unifor.locadorapost.locadorapost.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private List<Cliente> clientes;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarCliente(String documento) {
        if (documento == null || documento.isEmpty()) {
            return null;  // ou lançar uma exceção, caso preferir
        }
        return clientes.stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst().orElse(null);
    }    

    public List<Cliente> getClientes() {
        return clientes;
    }

    // Método para deletar o cliente
    public boolean deletarCliente(String documento) {
        Iterator<Cliente> iterator = clientes.iterator();
        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente.getDocumento().equals(documento)) {
                iterator.remove();  // Remove o cliente da lista
                return true;  // Retorna verdadeiro se o cliente foi removido
            }
        }
        return false;  // Retorna falso caso o cliente não tenha sido encontrado
    }
}
