package br.edu.utfpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import br.edu.utfpr.dto.ClienteDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.java.Log;

@Log
public class ClienteDAO {

    // Responsável por criar a tabela Cliente no banco.
    public ClienteDAO() {

        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela cliente ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE cliente (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_cliente_pk PRIMARY KEY," +
						"nome varchar(255)," +
						"telefone varchar(30)," + 
						"idade int," + 
                        "limiteCredito double," +
                        "id_pais int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean create(ClienteDTO cliente) throws SQLException {
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome, telefone, idade, limiteCredito, id_pais) values(?,?,?,?,?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setInt(3, cliente.getIdade());
            stmt.setDouble(4, cliente.getLimiteCredito());
            stmt.setInt(5, cliente.getPais().getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir: " + ex);
            return false;
        } finally {
            con.close();
        }
    }

    public boolean update(ClienteDTO cliente) throws SQLException {
        PreparedStatement stmt = null;
        Connection con = null;
        String sql = "UPDATE cliente SET nome = ?, telefone = ?, idade = ?, limiteCredito = ?, id_pais = ? WHERE id = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setInt(3, cliente.getIdade());
            stmt.setDouble(4, cliente.getLimiteCredito());
            stmt.setInt(5, cliente.getPais().getId());
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar: " + ex);
            return false;
        } finally {
            con.close();
        }
    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao deletar: " + ex);
            return false;
        } finally {
            con.close();
        }
    }

    public ClienteDTO search(String nome) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            stmt = con.prepareStatement("SELECT id, nome, telefone, idade, limiteCredito, id_pais FROM cliente WHERE name LIKE concat('%', ?,'%')");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            ClienteDTO cliente = null;
            while (rs.next()) {
                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setTelefone(rs.getString(3));
                cliente.setIdade(rs.getInt(4));
                cliente.setLimiteCredito(rs.getDouble(5));
                cliente.setPais(rs.getInt(6));
            }
            return cliente;
        } catch (SQLException ex) {
            System.err.println("Erro ao encontrar: " + ex);
            return null;
        } finally {
            con.close();
        }
    }
}