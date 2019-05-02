package br.edu.utfpr.negocio;

import br.edu.utfpr.excecao.NomeJaExisteException;
import java.util.List;

public abstract class AbstractNegocio<T> {
    
    public abstract void persistirEntidade (T entidade);

    public void incluir(T entidade) throws NomeJaExisteException {

        if (this.listar().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(entidade.getNome()))) {
            throw new NomeJaExisteException(entidade.getNome());
        }

        persistirEntidade (entidade);
    }

    public abstract List<T> listar();

}
