package br.edu.utfpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.dto.PaisDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;

@Log
public class ClienteDAO extends AbstractDAO<ClienteDTO> {

    // Responsável por criar a tabela Cliente no banco.
    public ClienteDAO() {

        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela cliente ...");
            conn.createStatement().executeUpdate(
                    "CREATE TABLE cliente ("
                    + "id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_cliente_pk PRIMARY KEY,"
                    + "nome varchar(255),"
                    + "telefone varchar(30),"
                    + "idade int,"
                    + "limiteCredito double,"
                    + "id_pais int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getStringSQLIncluir() {
        return "INSERT INTO cliente (nome, telefone, idade, limiteCredito, id_pais) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected void mapeamentoObjetoRelationalIncluir(PreparedStatement statement, ClienteDTO entidade) throws Exception {
        statement.setString(1, entidade.getNome());
        statement.setString(2, entidade.getTelefone());
        statement.setInt(3, entidade.getIdade());
        statement.setDouble(4, entidade.getLimiteCredito());
        statement.setInt(5, entidade.getPais().getId());

    }

    @Override
    protected String getStringSQLListar() {
        return "SELECT * FROM cliente";
    }

    @Override
    protected ClienteDTO populaObjetoListar(ResultSet result) throws Exception {
        return ClienteDTO.builder()
                .id(result.getInt("id"))
                .nome(result.getString("nome"))
                .telefone(result.getString("telefone"))
                .idade(result.getInt("idade"))
                .limiteCredito(result.getDouble("limiteCredito"))
                .pais(PaisDTO.builder().id(result.getInt("id_pais")).build())
                .build();
    }

    @Override
    protected String getStringSQLExcluir() {
        return "DELETE FROM cliente WHERE id=?";
    }

    @Override
    protected String getStringSQLAlterar() {
        return "UPDATE cliente SET nome=?, telefone=?, idade=?, limiteCredito=?, id_pais=? WHERE id=?";
    }

    @Override
    protected void mapeamentoObjetoRelationalAlterar(PreparedStatement statement, ClienteDTO entidade) throws Exception {
        statement.setString(1, entidade.getNome());
        statement.setString(2, entidade.getTelefone());
        statement.setInt(3, entidade.getIdade());
        statement.setDouble(4, entidade.getLimiteCredito());
        statement.setInt(5, entidade.getPais().getId());
        statement.setInt(6, entidade.getId());
    }
}
