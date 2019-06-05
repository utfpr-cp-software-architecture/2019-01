package br.edu.utfpr.apresentacao;

import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteModel {
    private Long id;
    private String nome;
    private int idade;
    private String telefone;
    private double limiteCredito;
    private String pais;

    public void setNome(String nome) throws NomeClienteMenor5CaracteresException {
        if (nome.length() < 5)
            throw new NomeClienteMenor5CaracteresException(nome);

        this.nome = nome;
    }
}