/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jéssica Ayumi Uehara
 */
public class TemplateMethodDAO {

    abstract String getConecctionDAO();
    abstract String getInsertSql();
    abstract String getListSql();
    abstract String getDeleteSql();
    abstract String getUpdateSql();

    abstract PreparedStatement incluirDAO(Object obj, PreparedStatement statement);
    abstract PreparedStatement alterarDAO(Object obj, PreparedStatement statement);
    abstract List<Object> preencherObjetoDAO(ResultSet obj);

    final boolean incluir(Object obj) {
        try (Connection conn = DriverManager.getConnection(getConecctionDAO())) {
            String sql = getInsertSql();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement = incluirDAO(obj, statement);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    final boolean alterar(Object obj) {
        try (Connection conn = DriverManager.getConnection(getConecctionDAO())) {
            String sql = getUpdateSql();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement = alterarDAO(obj, statement);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    final boolean excluir(int id) {
        try (Connection conn = DriverManager.getConnection(getConecctionDAO())) {
            String sql = getDeleteSql();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    final List<Object> listarTodos() {
        List<Object> resultado = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(getConecctionDAO())) {
            String sql = getListSql();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                resultado.add(preencherObjetoDAO(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }
}
