package pt.ul.fc.css.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pt.ul.fc.css.example.demo.Aprovacao;
import pt.ul.fc.css.example.demo.handlers.VotacoesEmCursoHandler;
import pt.ul.fc.css.example.demo.repositories.PropostaRepository;

@Controller
public class VotacoesEmCursoController {

  @Autowired private PropostaRepository propostaRepository;

  @Autowired VotacoesEmCursoHandler vCH = new VotacoesEmCursoHandler(propostaRepository);

  @GetMapping("/votacoesEmCurso")
  public ModelAndView votacoes(final Model model) {
    ModelAndView mv = new ModelAndView("votacoesEmCurso");
    mv.addObject("propostas", vCH.pedirListaDeVotacoes(Aprovacao.EM_CURSO));
    return mv;
  }
}
