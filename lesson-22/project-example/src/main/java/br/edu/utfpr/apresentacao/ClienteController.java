package br.edu.utfpr.apresentacao;

import java.util.ArrayList;
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

    ArrayList <String> paises = new ArrayList<>();
    
    public ClienteController(){    
        paises.add("Brasil");
        paises.add("Reino Unido");

        clientes = Stream.of(
            ClienteModel.builder().id(new Long(5)).nome("Lucas").idade(23).telefone("70").limiteCredito(20000).pais(paises.get(0)).build(),
            ClienteModel.builder().id(new Long(6)).nome("Marcos").idade(37).telefone("60").limiteCredito(15000).pais(paises.get(1)).build()
        ).collect(Collectors.toList());
    }

    @GetMapping ("/clientes")
    public String inicial(Model data){
        data.addAttribute("clientes", clientes);

        return "client-view";
    }

    @PostMapping ("/cliente/criar")
    public String criar(ClienteModel cliente){
        /*
        *   Atenção ao Pais, verificar como passar, o ClienteModel possui um 
        *   PaisDTO como atributo, provavelmente terá de vir direto do front
        */
        cliente.setId(new Long(clientes.size() + 1));
        clientes.add(cliente);
        return "redirect:/cliente";
    }

    @GetMapping ("/cliente/excluir")
    public String excluir(@RequestParam int id){
        clientes.remove(id - 1);

        return "redirect:/cliente";
    }

}