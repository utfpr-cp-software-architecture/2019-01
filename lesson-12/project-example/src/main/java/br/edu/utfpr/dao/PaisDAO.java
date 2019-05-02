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
public class PaisDAO extends TemplateDAO {

    // Responsável por criar a tabela País no banco
    public PaisDAO() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

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

    String getConecctionDAO() {
        return "jdbc:derby:memory:database";
    }

    String getListSql() {
        return "SELECT * FROM pais";
    }

    String getInsertSql() {
        return "INSERT INTO pais (nome, sigla, codigoTelefone) VALUES (?, ?, ?)";
    }

    String getUpdateSql() {
        return "UPDATE pais SET nome=?, sigla=?, codigoTelefone=? WHERE id=?";
    }

    String getDeleteSql() {
        return "DELETE FROM pais WHERE id=?";
    }

    PreparedStatement incluirDAO(Object obj, PreparedStatement statement) {
        PaisDTO pais = (PaisDTO) obj;
        statement.setString(1, pais.getNome());
        statement.setString(2, pais.getSigla());
        statement.setInt(3, pais.getCodigoTelefone());
        return statement;
    }

    PreparedStatement alterarDAO(Object obj, PreparedStatement statement) {
        PaisDTO pais = (PaisDTO) obj;
        statement.setString(1, pais.getNome());
        statement.setString(2, pais.getSigla());
        statement.setInt(3, pais.getCodigoTelefone());
        statement.setInt(4, pais.getId());
        return statement;
    }

    Object preencherObjetoDAO(ResultSet obj) {
        PaisDTO.builder()
                .codigoTelefone(obj.getInt("codigoTelefone"))
                .id(obj.getInt("id"))
                .nome(obj.getString("nome"))
                .sigla(obj.getString("sigla"))
                .build()

        return PaisDTO;
    }
}
