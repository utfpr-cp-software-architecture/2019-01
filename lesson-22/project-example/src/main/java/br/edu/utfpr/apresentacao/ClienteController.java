package br.edu.utfpr.apresentacao;

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
    private List<PaisModel> paises;
    public ClienteController(){
        clientes = Stream.of(
                ClienteModel.builder().id(1L).nome("Flavio").idade(18).telefone("33223322").limiteCredito(1000.0).build(),
                ClienteModel.builder().id(2L).nome("Evandro").idade(19).telefone("44339988").limiteCredito(1800.0).build()
        ).collect(Collectors.toList());


        paises = Stream.of(
                PaisModel.builder().id(new Long(1)).nome("Brasil").sigla("BR").codigoTelefone(55).build(),
                PaisModel.builder().id(new Long(2)).nome("Reino Unido").sigla("RU").codigoTelefone(44).build()
        ).collect(Collectors.toList());



    }

    @GetMapping("/cliente")
    public String inicial(Model data) {
        data.addAttribute("clientes", this.clientes);
        data.addAttribute("paises", )

        return "cliente-view";
    }

    @PostMapping("/cliente/criar")
    public String criar(ClienteModel cliente){
        cliente.setId(new Long(clientes.size() + 1));

        clientes.add(cliente);

        return "redirect:/cliente";
    }



}
