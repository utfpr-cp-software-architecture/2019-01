package br.edu.utfpr.apresentacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class ClienteModel {
    private Long id;
    private String nome;
    private int idade;
    private String telefone;
    private Double limiteCredito;

    @OneToMany
    private PaisModel pais;
}
