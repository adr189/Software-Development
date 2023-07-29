package pt.ul.fc.css.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pt.ul.fc.css.example.demo.handlers.ConsultarProjetosLeiHandler;
import pt.ul.fc.css.example.demo.repositories.ProjetoDeLeiRepository;

@Controller
public class ConsultarProjetosLeiController {

  @Autowired private ProjetoDeLeiRepository projetoDeLeiRepository;

  @Autowired
  private ConsultarProjetosLeiHandler cplh =
      new ConsultarProjetosLeiHandler(projetoDeLeiRepository);

  @GetMapping("/consultarProjetosLei")
  public ModelAndView projetosDeLei(final Model model) {
    ModelAndView mv = new ModelAndView("consultarProjetosLei");
    mv.addObject("projetosDeLei", cplh.consultarProjetosDeLeiNaoExpirados());
    return mv;
  }
}
