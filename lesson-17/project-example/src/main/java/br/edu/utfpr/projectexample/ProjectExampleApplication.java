package br.edu.utfpr.projectexample;

import br.edu.utfpr.projectexample.dao.CityDAO;
import br.edu.utfpr.projectexample.dao.StateDAO;
import br.edu.utfpr.projectexample.entity.City;
import br.edu.utfpr.projectexample.entity.State;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectExampleApplication implements CommandLineRunner {

    private final StateDAO stateDAO;
    private final CityDAO cityDAO;

    @Autowired
    public ProjectExampleApplication(StateDAO stateDAO, CityDAO cityDAO) {
        this.cityDAO = cityDAO;
        this.stateDAO = stateDAO;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of(
                State.builder().name("Paraná").build(),
                State.builder().name("Santa Catarina").build(),
                State.builder().name("Rio Grande do Sul").build()
        ).forEach(stateDAO::save);

        State parana = stateDAO.findAll()
                .stream()
                .filter(currentState -> currentState.getName().equalsIgnoreCase("Paraná"))
                .findAny()
                .orElseThrow();

        City city = City.builder().name("Cornélio Procópio").state(parana).build();
        
        cityDAO.save(city);
        
        stateDAO.findAll().forEach(System.out::println);
        cityDAO.findAll().forEach(System.out::println);
    }

}
