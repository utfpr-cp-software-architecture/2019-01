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
public class ClienteDAO {

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

    public boolean inserir(ClienteDTO cliente) {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = "INSERT INTO cliente (nome, telefone, idade, limiteCredito, id_pais) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getTelefone());
            statement.setInt(3, cliente.getIdade());
            statement.setDouble(4, cliente.getLimiteCredito());
            statement.setInt(5, cliente.getPais().getId());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<ClienteDTO> listarTodos() {

        List<ClienteDTO> resultado = new ArrayList<>();

        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = "SELECT * FROM cliente";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                resultado.add(
                        ClienteDTO.builder()
                                .id(result.getInt("id"))
                                .nome(result.getString("nome"))
                                .telefone(result.getString("telefone"))
                                .idade(result.getInt("idade"))
                                .limiteCredito(result.getDouble("limiteCredito"))
                                .pais(PaisDTO.builder().id(result.getInt("id_pais")).build())
                                .build()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public boolean alterar(ClienteDTO cliente) {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = "UPDATE cliente SET nome=?, telefone=?, idade=?, limiteCredito=?, id_pais=? WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getTelefone());
            statement.setInt(3, cliente.getIdade());
            statement.setDouble(4, cliente.getLimiteCredito());
            statement.setInt(5, cliente.getPais().getId());
            statement.setInt(6, cliente.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean excluir(int id) {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = "DELETE FROM cliente WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) 
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
