package br.edu.utfpr.apresentacao;

import br.edu.utfpr.dao.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ClienteController {

    private List<ClienteModel> clientes;

    public ClienteController() {
        PaisModel pais = PaisModel.builder().id(new Long(1)).nome("Brasil").sigla("BR").codigoTelefone(55).build();

        clientes = Stream.of(
        ClienteModel.builder().id(1).nome("Breno").idade(52).telefone("(18) 11111-1111").limiteCredito(250.00).pais(pais).build(),
        ClienteModel.builder().id(2).nome("Jean").idade(52).telefone("(18) 22222-2222").limiteCredito(500.00).pais(pais).build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/clientes")
    public String inicial(Model data) {

        data.addAttribute("clientes", clientes);

        return "cliente-view";
    }

    @PostMapping("/cliente/criar")
    public String criar(ClienteModel cliente) {

        cliente.setId(clientes.size() + 1);

        clientes.add(cliente);

        return "redirect:/cliente";
    }
}
