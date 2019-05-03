package br.edu.utfpr.dao;

import br.edu.utfpr.dto.PaisDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> {
    
    protected abstract String getStringSQLIncluir();
    protected abstract void mapeamentoObjetoRelationalIncluir(PreparedStatement statement, T entidade) throws Exception;
    
    public boolean incluir(T entidade) {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = getStringSQLIncluir();

            PreparedStatement statement = conn.prepareStatement(sql);
            mapeamentoObjetoRelationalIncluir(statement, entidade);
            
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) 
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    protected abstract String getStringSQLListar();
    protected abstract T populaObjetoListar(ResultSet result) throws Exception;
    
    public List<T> listarTodos() {

        List<T> resultado = new ArrayList<>();

        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = getStringSQLListar();

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next())
                resultado.add(populaObjetoListar (result));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    protected abstract String getStringSQLExcluir();
    
    public boolean excluir(int id) {

        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = getStringSQLExcluir();

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

    protected abstract String getStringSQLAlterar();
    protected abstract void mapeamentoObjetoRelationalAlterar(PreparedStatement statement, T entidade) throws Exception;
    
    public boolean alterar(T entidade) {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = getStringSQLAlterar();

            PreparedStatement statement = conn.prepareStatement(sql);
            
            mapeamentoObjetoRelationalAlterar(statement, entidade);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) 
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
