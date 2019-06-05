package br.edu.utfpr.apresentacao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteController {

    private List<ClienteModel> clientes;

    public ClienteController() {
        clientes = Stream.of(
            ClienteModel.builder().id(new Long(1)).nome("Kamilla").idade(20).telefone("45678901").limiteCredito(1500.50).pais(PaisModel.builder().sigla("BR").build()).build(),
            ClienteModel.builder().id(new Long(2)).nome("Gustavo").idade(21).telefone("12345678").limiteCredito(960.00).pais(PaisModel.builder().sigla("BR").build()).build()
        ).collect(Collectors.toList());
    }

    @GetMapping ("/cliente")
    public String inicial(Model data) {

        data.addAttribute("clientes", clientes);

        return "cliente-view";
    }

    @PostMapping ("/cliente/criar")
    public String criar(ClienteModel cliente) {

        cliente.setId(new Long(clientes.size() + 1));

        clientes.add(cliente);

        return "redirect:/cliente";
    }

}