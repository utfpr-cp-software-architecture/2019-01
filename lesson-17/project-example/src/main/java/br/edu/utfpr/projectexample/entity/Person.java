package br.edu.utfpr.projectexample.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Person implements Serializable {

    @Id @GeneratedValue
    protected Long id;
    protected String name;
    
    @ManyToOne
    protected City city;
}
