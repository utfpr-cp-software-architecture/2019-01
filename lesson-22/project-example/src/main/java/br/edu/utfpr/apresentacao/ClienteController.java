package br.edu.utfpr.apresentacao;

import br.edu.utfpr.dao.Pais;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ClienteController {
    private List<ClienteModel> clientes;
    private List<PaisModel> paises;

    public ClienteController() {
        clientes = Stream.of(
                ClienteModel.builder().id(new Long(1)).nome("Jordan").idade(20).telefone("99287182")
                .limiteCredito(500).pais(new Pais().builder().nome("Africa").sigla("AF").codigoTelefone(29).build())
                .build()
        ).collect(Collectors.toList());

        paises = Stream.of(
                PaisModel.builder().nome("Africa").sigla("AF").codigoTelefone(29).build(),
                PaisModel.builder().nome("Alemanha").sigla("AL").codigoTelefone(18).build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/clientes")
    public String inicial(Model data) {

        data.addAttribute("clientes", clientes);
        data.addAttribute("paises", paises);

        return "clientes-view";
    }

    @PostMapping("/clientes/criar")
    public String criar(ClienteModel cliente) {

        cliente.setId(new Long(clientes.size() + 1));


        clientes.add(cliente);



        return "redirect:/clientes";
    }

//    @GetMapping ("/cliente/excluir")
//    public String excluir (@RequestParam int id) {
//        clientes.remove(id - 1);
//
//        return "redirect:/clientes";
//    }

//    @GetMapping ("/clientes/prepara-alterar")
//    public String preparaAlterar (@RequestParam int id, Model data){
//
//        ClienteModel cliente = clientes.get(id - 1);
//
//        data.addAttribute("clienteAtual", cliente);
//        data.addAttribute("clientes", clientes);
//
//        return "clientes-view";
//    }
}
