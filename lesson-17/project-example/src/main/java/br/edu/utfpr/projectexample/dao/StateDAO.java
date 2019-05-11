package br.edu.utfpr.projectexample.dao;

import br.edu.utfpr.projectexample.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateDAO extends JpaRepository<State, Long>{
    
}
