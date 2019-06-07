
package br.edu.utfpr.apresentacao;

import br.edu.utfpr.dao.Pais;
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
            ClienteModel.builder()
                    .id(new Long(1))
                    .nome("Stefanie")
                    .idade(21)
                    .telefone("3322-0000")
                    .limiteCredito(22000)
                    .pais(PaisModel.builder().nome("Brasil").build()).build()
        ).collect(Collectors.toList());
    }

    @GetMapping ("/clientes")
    public String inicial(Model data) {

        data.addAttribute("clientes", clientes);

        return "cliente-view";
    }

    @PostMapping ("/clientes/criar")
    public String criar(ClienteModel cliente) {

        cliente.setId(new Long(clientes.size() + 1));

        clientes.add(cliente);

        return "redirect:/cliente";
    }

    @GetMapping ("/clientes/excluir")
    public String excluir (@RequestParam int id) {
        clientes.remove(id - 1);

        return "redirect:/clientes";
    }

    @GetMapping ("/clientes/prepara-alterar")
    public String preparaAlterar (@RequestParam int id, Model data){

        ClienteModel cliente = clientes.get(id - 1);
        
        data.addAttribute("clienteAtual", cliente);
        data.addAttribute("clientes", clientes);

        return "cliente-view";
    }
}
    

