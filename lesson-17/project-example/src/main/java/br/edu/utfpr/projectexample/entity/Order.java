package br.edu.utfpr.projectexample.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity (name = "Sale_Order")
public class Order implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    private double total;
    
    @Temporal (TemporalType.DATE)
    private Date orderDate;
    
    @ManyToOne
    private Customer customer;
    
    @ElementCollection
    private Set<ItemOrder> items;
}
