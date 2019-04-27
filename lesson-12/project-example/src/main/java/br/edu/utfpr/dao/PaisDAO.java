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
public class PaisDAO {

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

    public boolean inserir(PaisDTO pais) {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = "INSERT INTO pais (nome, sigla, codigoTelefone) VALUES (?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, pais.getNome());
            statement.setString(2, pais.getSigla());
            statement.setInt(3, pais.getCodigoTelefone());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<PaisDTO> listarTodos() {

        List<PaisDTO> resultado = new ArrayList<>();

        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = "SELECT * FROM pais";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()) {

                resultado.add(
                        PaisDTO.builder()
                                .codigoTelefone(result.getInt("codigoTelefone"))
                                .id(result.getInt("id"))
                                .nome(result.getString("nome"))
                                .sigla(result.getString("sigla"))
                                .build()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public boolean excluir(int id) {

        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = "DELETE FROM pais WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean alterar(PaisDTO pais) {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = "UPDATE pais SET nome=?, sigla=?, codigoTelefone=? WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, pais.getNome());
            statement.setString(2, pais.getSigla());
            statement.setInt(3, pais.getCodigoTelefone());
            statement.setInt(4, pais.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) 
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }


}
