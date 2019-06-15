package br.edu.utfpr.dao;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cliente implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private int idade;
    private String telefone;
    private double limiteCredito;
    
    @ManyToOne
    private Pais pais;
    
}
