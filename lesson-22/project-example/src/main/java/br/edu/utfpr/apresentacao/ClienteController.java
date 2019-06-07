package br.edu.utfpr.apresentacao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        paises = Stream.of(
            PaisModel.builder().id(1L).nome("Brasil").sigla("BR").codigoTelefone(55).build(),
            PaisModel.builder().id(2L).nome("Reino Unido").sigla("RU").codigoTelefone(44).build()
        ).collect(Collectors.toList());


        clientes = Stream.of(
                ClienteModel.builder().id(1L).nome("Pedro").idade(20).telefone("00000000").pais(paises.get(0)).limiteCredito(1000.0).build(),
                ClienteModel.builder().id(2L).nome("Felipe").idade(20).telefone("444444444").pais(paises.get(1)).limiteCredito(2000.0).build()
        ).collect(Collectors.toList());
    }

    @GetMapping ("/cliente")
    public String inicial(Model data) {

        data.addAttribute("clientes", clientes);
        data.addAttribute("paises", paises);
        return "cliente-view";
    }

    @PostMapping ("/cliente/criar")
    public String criar(ClienteModel cliente, @RequestParam (value ="paisId") long paisId) {

        cliente.setId((long) (clientes.size() + 1));
        paises.stream().filter(e -> e.getId().equals(paisId)).forEach(cliente::setPais);

        clientes.add(cliente);

        return "redirect:/cliente";
    }

    @GetMapping ("/cliente/excluir")
    public String excluir (@RequestParam long id) {
        clientes.removeIf(c -> c.getId().equals(id));

        return "redirect:/cliente";
    }
}