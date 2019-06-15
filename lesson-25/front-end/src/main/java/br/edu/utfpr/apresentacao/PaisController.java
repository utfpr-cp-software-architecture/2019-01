package br.edu.utfpr.apresentacao;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaisController {

    @GetMapping("/pais")
    public String inicial(Model data) throws JsonSyntaxException, UnirestException {

        PaisModel arrayPaises[] = new Gson()
                    .fromJson(
                        Unirest
                            .get("http://localhost:8081/servico/pais")
                            .asJson()
                            .getBody()
                            .toString(), 
                        PaisModel[].class
                    );

        data.addAttribute("paises", arrayPaises);

        return "pais-view";
    }

    @PostMapping ("/pais/criar")
    public String criar(PaisModel pais) throws UnirestException {

            Unirest.post("http://localhost:8081/servico/pais")
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(pais, PaisModel.class))
                .asJson();

        return "redirect:/pais";
    }

    @GetMapping ("/pais/excluir")
    public String excluir (@RequestParam int id) throws UnirestException {

        Unirest
            .delete("http://localhost:8081/servico/pais/{id}")
            .routeParam("id", String.valueOf(id))
            .asJson();

        return "redirect:/pais";
    }

    @GetMapping ("/pais/prepara-alterar")
    public String preparaAlterar (@RequestParam int id, Model data) throws JsonSyntaxException, UnirestException {

        PaisModel paisExistente = new Gson()
            .fromJson(
                Unirest
                    .get("http://localhost:8081/servico/pais/{id}")
                    .routeParam("id", String.valueOf(id))
                    .asJson()
                    .getBody()
                    .toString(),
                PaisModel.class
            );

        data.addAttribute("paisAtual", paisExistente);

        PaisModel arrayPaises[] = new Gson()
        .fromJson(
            Unirest
                .get("http://localhost:8081/servico/pais")
                .asJson()
                .getBody()
                .toString(), 
            PaisModel[].class
        );

        data.addAttribute("paises", arrayPaises);

        return "pais-view-alterar";
    }

    @PostMapping ("/pais/alterar")
    public String alterar (PaisModel paisAlterado) throws UnirestException {

        Unirest
            .put("http://localhost:8081/servico/pais/{id}")
            .routeParam("id", String.valueOf(paisAlterado.getId()))
            .header("Content-type", "application/json")
            .header("accept", "application/json")
            .body(new Gson().toJson(paisAlterado, PaisModel.class))
            .asJson();

        return "redirect:/pais";
    }
}