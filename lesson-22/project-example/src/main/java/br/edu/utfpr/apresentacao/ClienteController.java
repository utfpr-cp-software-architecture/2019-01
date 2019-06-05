/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.apresentacao;

import br.edu.utfpr.dao.Pais;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aluno
 */
public class ClienteController {
    
    
    private List<ClienteModel> clientes;

    public ClienteController() {
        clientes = Stream.of(
            ClienteModel.builder().id(new Long(1)).nome("Joao").idade(25).telefone("999").limiteCredito(200.0).build(),
           ClienteModel.builder().id(new Long(2)).nome("Pedro").idade(25).telefone("888").limiteCredito(200.0).build()
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

   

    @GetMapping ("/cliente/cliente-listar")
    public List<ClienteModel> clienteListar (){
        
        return clientes;
    }
}

