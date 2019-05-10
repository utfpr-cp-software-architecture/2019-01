package br.edu.utfpr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource (path = "/pais")
public interface PaisDAO extends JpaRepository<Pais, Long>{ }
