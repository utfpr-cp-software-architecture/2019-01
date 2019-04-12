package br.edu.utfpr.excecao;

class NomeClienteJaExisteException extends Exception {
    NomeClienteJaExisteException (String descricao) {
        super(descricao);
    }
}