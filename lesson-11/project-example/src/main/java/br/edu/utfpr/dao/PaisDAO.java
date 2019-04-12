package br.edu.utfpr.dao;

import br.edu.utfpr.dto.PaisDTO;

@Log
public class PaisDAO {

    // Responsável por criar a tabela País no banco
    public PaisDAO() {
        try (Connection conn = DriverManager.getDriver("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela pais ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE pais (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_pais_pk PRIMARY KEY," +
						"nome varchar(255)," +
						"sigla varchar(3)," + 
						"codigoTelefone int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}