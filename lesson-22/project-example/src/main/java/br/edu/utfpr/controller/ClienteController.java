package br.edu.utfpr.controller;

import br.edu.utfpr.dao.Cliente;
import br.edu.utfpr.model.ClienteModel;
import br.edu.utfpr.model.PaisModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ClienteController {

    private List<ClienteModel> clientes;
    private List<PaisModel> paises;

    public ClienteController() {
        clientes = Stream.of(
//            PaisModel.builder().id(new Long(1)).nome("Brasil").sigla("BR").codigoTelefone(55).build(),
//            PaisModel.builder().id(new Long(2)).nome("Reino Unido").sigla("RU").codigoTelefone(44).build()
                ClienteModel.builder().id(1).nome("Mateus").idade(18).telefone("1498989697").limiteCredito(2.2).paisNome("Brasil").build()
        ).collect(Collectors.toList());
        paises = Stream.of(
                PaisModel.builder().id(new Long(1)).nome("Brasil").sigla("BR").codigoTelefone(55).build(),
                PaisModel.builder().id(new Long(2)).nome("Reino Unido").sigla("RU").codigoTelefone(44).build(),
                PaisModel.builder().id(new Long(3)).nome("Colombia").sigla("CO").codigoTelefone(57).build(),
                PaisModel.builder().id(new Long(4)).nome("Guatemala").sigla("GT").codigoTelefone(502).build()
        ).collect(Collectors.toList());
    }

    @GetMapping ("/clientes")
    public String inicial(Model data) {

        data.addAttribute("clientes", clientes);
        data.addAttribute("paises", paises);

        return "clientes-view";
    }

    @PostMapping ("/clientes/criar")
    public String criar(ClienteModel cliente) {

        cliente.setId(clientes.size() + 1);

        clientes.add(cliente);

        return "redirect:/clientes";
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

        return "clientes-view";
    }
}