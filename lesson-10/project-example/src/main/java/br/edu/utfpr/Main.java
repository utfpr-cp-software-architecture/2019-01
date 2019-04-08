package br.edu.utfpr;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();

    try {
        cliente.incluir ("John", 19, "123231", 100.0, new Pais());
    
    } catch (Exception e) {
        //TODO: handle exception
    }
    }
}

class Cliente {

    String nome;
    int idade;
    String telefone;
    double limiteCredito;
    Pais pais;

    public void incluir(String nome, int idade, String telefone, double limiteCredito, Pais pais) throws NomeClienteJaExisteException {

        if (this.listar().stream().anyMatch(c -> c.nome.equalsIgnoreCase(nome)))
            throw new NomeClienteJaExisteException(nome);


    }

    public List<Cliente> listar() {
        throw new UnsupportedOperationException();
    }
}

class NomeClienteJaExisteException extends Exception {
    NomeClienteJaExisteException (String descricao) {
        super(descricao);
    }
}

class Pais {

    public void incluir(String nome, String sigla) {

    }

    public List listar() {
        throw new UnsupportedOperationException();
    }
}