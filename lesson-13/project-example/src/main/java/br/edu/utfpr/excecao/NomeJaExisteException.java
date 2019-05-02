package br.edu.utfpr.excecao;

public class NomeJaExisteException extends Exception {
    public NomeJaExisteException (String descricao) {
        super(descricao);
    }
}