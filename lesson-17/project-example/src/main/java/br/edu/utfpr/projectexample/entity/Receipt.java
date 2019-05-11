package br.edu.utfpr.projectexample.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Receipt implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    private Long generatedNumber;
    private double total;
    
    @OneToOne
    private Order order;
}
