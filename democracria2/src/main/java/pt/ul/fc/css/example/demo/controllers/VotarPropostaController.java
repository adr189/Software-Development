package pt.ul.fc.css.example.demo.controllers;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ul.fc.css.example.demo.entities.Cidadao;
import pt.ul.fc.css.example.demo.entities.Proposta;
import pt.ul.fc.css.example.demo.handlers.VotarPropostaHandler;
import pt.ul.fc.css.example.demo.repositories.CidadaoRepository;
import pt.ul.fc.css.example.demo.repositories.DelegadoPropostaRepository;
import pt.ul.fc.css.example.demo.repositories.PropostaRepository;

@Controller
public class VotarPropostaController {

  @Autowired PropostaRepository pr;

  @Autowired DelegadoPropostaRepository dr;

  @Autowired CidadaoRepository cr;

  @GetMapping("/votarProposta")
  public String votarPropostaPagina() {
    return "votarProposta";
  }

  @PostMapping("/votarProposta")
  public void votarProposta(
      @RequestParam("id_prop") String propId, @RequestParam("decision") String decision)
      throws Exception {
    VotarPropostaHandler vph = new VotarPropostaHandler(pr, dr);
    Proposta propCorr = pr.findByID(Long.valueOf(propId));
    Boolean votacao = true;
    if (decision.equals("T")) {
      votacao = true;
    } else if (decision.equals("F")) {
      votacao = false;
    }
    List<Cidadao> listaCid = cr.getList();
    Random rand = new Random();
    Cidadao cidadaoRandom = listaCid.get(rand.nextInt(listaCid.size()));
    pr.save(vph.votarNumaProposta2(cidadaoRandom, propCorr, votacao));
  }
}
