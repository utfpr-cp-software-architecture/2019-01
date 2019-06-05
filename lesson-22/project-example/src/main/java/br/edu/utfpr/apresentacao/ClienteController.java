
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
            ClienteModel.builder().id(new Long(1)).nome("feliep").idade(89).telefone("9999-9999").limiteCredito(5646).
                    pais(PaisModel.builder().
                            id(new Long(2)).
                            nome("Reino Unido").
                            sigla("RU").
                            codigoTelefone(44).
                            build()
                    ).build()
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


