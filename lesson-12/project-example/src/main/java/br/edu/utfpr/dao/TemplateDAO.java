package br.edu.utfpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import lombok.extern.java.Log;

@Log
public abstract class TemplateDAO <T>{

    private Connection conn = null; //perguntar

    public Connection TemplateDAO(){
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")){
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract boolean insere(T objeto);

    public abstract T busca(int id);

    public abstract boolean update(T objeto);

    public abstract boolean deleta(int id);

    public abstract <T> List<T> listaTodos();




}
