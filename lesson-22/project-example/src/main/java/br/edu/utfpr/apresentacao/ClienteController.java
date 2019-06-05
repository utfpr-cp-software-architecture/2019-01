package br.edu.utfpr.apresentacao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ClienteController {
    private List<ClienteModel> clientes;

    public ClienteController(){
        clientes = Stream.of(
                ClienteModel.builder().id(1L).nome("Flavio").idade(18).telefone("33223322").limiteCredito(1000.0).build(),
                ClienteModel.builder().id(2L).nome("Evandro").idade(19).telefone("44339988").limiteCredito(1800.0).build()
        ).collect(Collectors.toList());
    }

    @GetMapping
    public String inicial(Model data) {
        data.addAttribute("clientes", this.clientes);

        return "cliente-view";
    }
}
