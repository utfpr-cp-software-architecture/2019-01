package br.edu.utfpr.projectexample.dao;

import br.edu.utfpr.projectexample.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDAO extends JpaRepository<City, Long>{
    
}
