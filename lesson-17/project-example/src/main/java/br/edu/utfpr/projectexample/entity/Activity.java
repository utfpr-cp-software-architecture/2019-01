package br.edu.utfpr.projectexample.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Activity implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    private String description;
    
    @ManyToMany (mappedBy = "activities")
    private Set<Employee> employees;
}
