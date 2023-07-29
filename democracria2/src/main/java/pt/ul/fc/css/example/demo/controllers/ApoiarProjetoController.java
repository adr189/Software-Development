package pt.ul.fc.css.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ul.fc.css.example.demo.entities.Cidadao;
import pt.ul.fc.css.example.demo.entities.ProjetoDeLei;
import pt.ul.fc.css.example.demo.handlers.ApoiarProjetoHandler;
import pt.ul.fc.css.example.demo.repositories.CidadaoRepository;
import pt.ul.fc.css.example.demo.repositories.ProjetoDeLeiRepository;
import pt.ul.fc.css.example.demo.repositories.PropostaRepository;

@Controller
public class ApoiarProjetoController {

  @Autowired ProjetoDeLeiRepository plr;

  @Autowired PropostaRepository pr;

  @Autowired CidadaoRepository cr;

  @GetMapping("/apoiarProjeto")
  public String apoiarProjetoPagina() {
    return "apoiarProjeto";
  }

  @PostMapping("/apoiarProjeto")
  public void apoiarProjeto(@RequestParam("id") String projectId) {
    ApoiarProjetoHandler aph = new ApoiarProjetoHandler(plr, pr);
    List<Cidadao> listaDeCidadaos = cr.getListCidadao();
    Random rand = new Random();
    int randomCidadao = rand.nextInt(listaDeCidadaos.size());

    Optional<ProjetoDeLei> pp = plr.findById(Long.valueOf(projectId));
    if (pp.isPresent()) {
      ProjetoDeLei pp2 = pp.get();
      System.out.println(pp2.getProposto().getNome());
      aph.apoiarprojeto(pp2, listaDeCidadaos.get(randomCidadao));
    }
    System.out.println("Votou no projeto com ID: " + projectId);
  }
}
