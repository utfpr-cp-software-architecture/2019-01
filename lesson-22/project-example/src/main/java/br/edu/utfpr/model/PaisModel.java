package br.edu.utfpr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaisModel {
    private Long id;
    private String nome;
    private String sigla;
    private int codigoTelefone;
    
}