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
public class PaisController {

    private List<PaisModel> paises;

    public PaisController() {
        paises = Stream.of(
            PaisModel.builder().id(new Long(1)).nome("Brasil").sigla("BR").codigoTelefone(55).build(),
            PaisModel.builder().id(new Long(2)).nome("Reino Unido").sigla("RU").codigoTelefone(44).build()
        ).collect(Collectors.toList());
    }

    @GetMapping ("/pais")
    public String inicial(Model data) {

        data.addAttribute("paises", paises);

        return "pais-view";
    }

    @PostMapping ("/pais/criar")
    public String criar(PaisModel pais) {

        pais.setId(new Long(paises.size() + 1));

        paises.add(pais);

        return "redirect:/pais";
    }

    @GetMapping ("/pais/excluir")
    public String excluir (@RequestParam int id) {
        paises.remove(id - 1);

        return "redirect:/pais";
    }

    @GetMapping ("/pais/prepara-alterar")
    public String preparaAlterar (@RequestParam int id, Model data){

        PaisModel pais = paises.get(id - 1);
        
        data.addAttribute("paisAtual", pais);
        data.addAttribute("paises", paises);

        return "pais-view";
    }
}