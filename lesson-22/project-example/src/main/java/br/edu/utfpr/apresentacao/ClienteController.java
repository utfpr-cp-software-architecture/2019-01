package br.edu.utfpr.apresentacao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class ClienteController { 
    private List<ClienteModel> clientes;
    private PaisModel brasil = PaisModel.builder().id(new Long(1)).nome("Brasil").sigla("BR").codigoTelefone(55).build();
    private PaisModel usa =    PaisModel.builder().id(new Long(2)).nome("Estados Unidos").sigla("US").codigoTelefone(44).build();

    
    public ClienteController(){
        clientes = Stream.of(
                ClienteModel.builder().id(new Long(1)).nome("Thiago").idade(21).telefone("+55 11 97443-4444").limiteCredito(500).pais(brasil).build(),
                ClienteModel.builder().id(new Long(2)).nome("Pedro").idade(20).telefone("+55 11 97433-33333").limiteCredito(500).pais(usa).build()
        ).collect(Collectors.toList());
    }
    
    @GetMapping ("/cliente")
    public String inicial(Model data){
        data.addAttribute("clientes", clientes);
        
        return "cliente-view";
    }
    
    @PostMapping ("/clinte/incluir")
    public String incluir(ClienteModel cliente){
       cliente.setId(new Long(clientes.size() + 1));
       
       clientes.add(cliente);
       
       return "redirect:cliente";
    }
}
