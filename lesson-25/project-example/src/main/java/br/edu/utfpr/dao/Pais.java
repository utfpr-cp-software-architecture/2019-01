package br.edu.utfpr.dao;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pais implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String sigla;
    private int codigoTelefone;
}
