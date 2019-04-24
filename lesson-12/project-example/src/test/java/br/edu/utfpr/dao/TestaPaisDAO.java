package br.edu.utfpr.dao;

import br.edu.utfpr.dto.PaisDTO;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public abstract class TestaPaisDAO {

    private static PaisDAO paisDAO;

    @BeforeClass
    public static void setup() {
        //paisDAO = new PaisDAO();
    }

    @Test
    public void testaIncluirPais() {

        PaisDTO pais = PaisDTO.builder()
                .codigoTelefone(55)
                .nome("Brasil")
                .sigla("BR")
                .build();

        Assert.assertTrue(paisDAO.insere(pais));
    }

    @Test
    public void testaListarTodos() {
        PaisDTO pais = PaisDTO.builder()
                .codigoTelefone(55)
                .nome("Brasil")
                .sigla("BR")
                .build();

        paisDAO.insere(pais);

        Assert.assertTrue(paisDAO.listaTodos().size() > 0);
    }

    @Test
    public void testaExcluir() {

        PaisDTO pais = PaisDTO.builder()
                .codigoTelefone(32)
                .nome("Estados Unidos da América")
                .sigla("EUA")
                .build();

        paisDAO.insere(pais);

        PaisDTO paisRecuperado = paisDAO.listaTodos().stream().filter(p -> p.getSigla().equalsIgnoreCase(pais.getSigla())).collect(Collectors.toList()).get(0);

        Assert.assertTrue(paisDAO.deleta(paisRecuperado.getId()));
    }

    @Test
    public void testaAlterar() {
        PaisDTO pais = PaisDTO.builder()
                .codigoTelefone(32)
                .nome("Reino Unido")
                .sigla("RU")
                .build();

        paisDAO.insere(pais);

        PaisDTO paisRecuperado = paisDAO.listaTodos().stream().filter(p -> p.getSigla().equalsIgnoreCase(pais.getSigla())).collect(Collectors.toList()).get(0);

        paisRecuperado.setNome("Reino Unido Alterado");

        Assert.assertTrue(paisDAO.update(paisRecuperado));
    }

    @Test
    public void testaListarPorId() {
        PaisDTO pais = PaisDTO.builder()
                .codigoTelefone(32)
                .nome("Alemanha")
                .sigla("AL")
                .build();

        paisDAO.insere(pais);

        PaisDTO paisRecuperado = paisDAO.listaTodos().stream().filter(p -> p.getSigla().equalsIgnoreCase(pais.getSigla())).collect(Collectors.toList()).get(0);
        
        Assert.assertTrue(paisDAO.busca (paisRecuperado.getId()).getNome().equals(pais.getNome()));

    }

}
