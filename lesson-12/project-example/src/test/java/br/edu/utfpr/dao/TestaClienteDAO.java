package br.edu.utfpr.dao;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestaClienteDAO {

    private static ClienteDAO clienteDAO;

    @BeforeClass
    public static void setup() {
        clienteDAO = new ClienteDAO();
    }

    @Test
    public void testaInserir() {

        ClienteDTO cliente = ClienteDTO.builder()
                .idade(14)
                .limiteCredito(100.0)
                .nome("John Connor")
                .pais(PaisDTO.builder().id(1).build())
                .telefone("123")
                .build();

        Assert.assertTrue(clienteDAO.inserir(cliente));
    }

    @Test
    public void testaListar() {

        ClienteDTO cliente = ClienteDTO.builder()
                .idade(32)
                .limiteCredito(100.0)
                .nome("Sara Connor")
                .pais(PaisDTO.builder().id(13).build())
                .telefone("321")
                .build();

        clienteDAO.inserir(cliente);

        Assert.assertTrue(clienteDAO.listarTodos().size() > 0);

    }

    @Test
    public void testaAlterar() throws NomeClienteMenor5CaracteresException {

        ClienteDTO cliente = ClienteDTO.builder()
                .idade(14)
                .limiteCredito(100.0)
                .nome("John Doe")
                .pais(PaisDTO.builder().id(1).build())
                .telefone("123")
                .build();

        clienteDAO.inserir(cliente);

        ClienteDTO clienteNoBanco = clienteDAO.listarTodos()
                .stream()
                .filter(clienteAtual -> clienteAtual.getNome().equals(cliente.getNome()))
                .findAny()
                .orElseThrow(RuntimeException::new);

        clienteNoBanco.setNome("Anna Doe");

        Assert.assertTrue(clienteDAO.alterar(clienteNoBanco));
    }

    @Test
    public void testaExcluir() {

        ClienteDTO cliente = ClienteDTO.builder()
                .idade(14)
                .limiteCredito(100.0)
                .nome("Neil Armstrong")
                .pais(PaisDTO.builder().id(1).build())
                .telefone("123")
                .build();

        clienteDAO.inserir(cliente);

        ClienteDTO clienteNoBanco = clienteDAO.listarTodos()
                .stream()
                .filter(clienteAtual -> clienteAtual.getNome().equals(cliente.getNome()))
                .findAny()
                .orElseThrow(RuntimeException::new);
        
        Assert.assertTrue(clienteDAO.excluir(clienteNoBanco.getId()));
    }
}
