package br.edu.utfpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import br.edu.utfpr.dto.PaisDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;

@Log
public class PaisDAO extends AbstractDAO<PaisDTO> {

    // Responsável por criar a tabela País no banco
    public PaisDAO() {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela pais ...");
            conn.createStatement().executeUpdate(
                    "CREATE TABLE pais ("
                    + "id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_pais_pk PRIMARY KEY,"
                    + "nome varchar(255),"
                    + "sigla varchar(3),"
                    + "codigoTelefone int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PaisDTO listarPorId(int id) {
        return this.listarTodos().stream().filter(p -> p.getId() == id).findAny().orElseThrow(RuntimeException::new);
    }

    @Override
    protected String getStringSQLIncluir() {
        return "INSERT INTO pais (nome, sigla, codigoTelefone) VALUES (?, ?, ?)";
    }

    @Override
    protected void mapeamentoObjetoRelationalIncluir(PreparedStatement statement, PaisDTO entidade) throws Exception {
        statement.setString(1, entidade.getNome());
        statement.setString(2, entidade.getSigla());
        statement.setInt(3, entidade.getCodigoTelefone());
    }

    @Override
    protected String getStringSQLListar() {
        return "SELECT * FROM pais";
    }

    @Override
    protected PaisDTO populaObjetoListar(ResultSet result) throws Exception {
        return PaisDTO.builder()
                .codigoTelefone(result.getInt("codigoTelefone"))
                .id(result.getInt("id"))
                .nome(result.getString("nome"))
                .sigla(result.getString("sigla"))
                .build();
    }

    @Override
    protected String getStringSQLExcluir() {
        return "DELETE FROM pais WHERE id=?";
    }

    @Override
    protected String getStringSQLAlterar() {
        return "UPDATE pais SET nome=?, sigla=?, codigoTelefone=? WHERE id=?";
    }

    @Override
    protected void mapeamentoObjetoRelationalAlterar(PreparedStatement statement, PaisDTO entidade) throws Exception {
        statement.setString(1, entidade.getNome());
        statement.setString(2, entidade.getSigla());
        statement.setInt(3, entidade.getCodigoTelefone());
        statement.setInt(4, entidade.getId());
    }

}
