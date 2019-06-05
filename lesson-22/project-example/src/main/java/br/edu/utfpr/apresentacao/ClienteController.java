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
                ClienteModel.builder().id(new Long(1)).nome("João").idade(45).telefone("99996666")
                        .limiteCredito(new Double("1500")).build(),
                ClienteModel.builder().id(new Long(2)).nome("Renata").idade(30).telefone("88885555")
                        .limiteCredito(new Double("1000")).build())
                .collect(Collectors.toList());
    }

    @GetMapping("/cliente")
    public String inicial(Model data) {

        data.addAttribute("clientes", clientes);

        return "cliente-view";
    }

    @PostMapping("/cliente/criar")
    public String criar(ClienteModel cliente) {

        cliente.setId(new Long(clientes.size() + 1));

        clientes.add(cliente);

        return "redirect:/cliente";
    }

    @GetMapping("/pais/excluir")
    public String excluir(@RequestParam int id) {
        clientes.remove(id - 1);

        return "redirect:/pais";
    }

    private ClienteModel findById(int id) {
        for (ClienteModel cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
    }

}