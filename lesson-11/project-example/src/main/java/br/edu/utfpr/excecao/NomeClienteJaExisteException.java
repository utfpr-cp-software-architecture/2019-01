package br.edu.utfpr.excecao;

public class NomeClienteJaExisteException extends Exception {
    public NomeClienteJaExisteException (String descricao) {
        super(descricao);
    }
}