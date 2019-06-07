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
    private List<PaisModel> paises;

    public ClienteController() {
        paises = Stream.of(
            PaisModel.builder().id(new Long(1)).nome("Brasil").sigla("BR").codigoTelefone(55).build(),
            PaisModel.builder().id(new Long(2)).nome("Reino Unido").sigla("RU").codigoTelefone(44).build()
        ).collect(Collectors.toList());
        
        clientes = Stream.of(
            ClienteModel.builder().id(1).nome("Rodrigo Miyashiro").idade(23).telefone("(43) 99140-5276").limiteCredito(0).pais(paises.get(0)).build(),
            ClienteModel.builder().id(2).nome("Calango deDale").idade(18).telefone("(43) 92354-6513").limiteCredito(6513546).pais(paises.get(1)).build()
        ).collect(Collectors.toList());
    }

    @GetMapping ("/cliente")
    public String inicial(Model data) {

        data.addAttribute("clientes", clientes);
        data.addAttribute("paises", paises);

        return "cliente-view";
    }

    @PostMapping("/cliente/criar")
    public String criar(ClienteModel cliente, @RequestParam int paisId) {

        cliente.setId(clientes.size() + 1);
        for(PaisModel pais: paises){
            if(paisId == pais.getId())
                cliente.setPais(pais);
        }
        clientes.add(cliente);

        return "redirect:/clientes";
    }
}