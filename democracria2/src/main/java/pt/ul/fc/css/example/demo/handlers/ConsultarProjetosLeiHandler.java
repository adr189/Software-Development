package pt.ul.fc.css.example.demo.handlers;

import java.util.List;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.ProjetoDeLei;
import pt.ul.fc.css.example.demo.repositories.ProjetoDeLeiRepository;

@Component
public class ConsultarProjetosLeiHandler {

  private ProjetoDeLeiRepository pdlr;

  public ConsultarProjetosLeiHandler(ProjetoDeLeiRepository pdlr) {
    this.pdlr = pdlr;
  }

  public List<ProjetoDeLei> consultarProjetosDeLeiNaoExpirados() {
    List<ProjetoDeLei> listaProjetosDeLei = pdlr.findAll();
    for (ProjetoDeLei pdl : listaProjetosDeLei) {
      if (pdl.isEstado()) {
        listaProjetosDeLei.remove(pdl);
      }
    }
    return listaProjetosDeLei;
  }
}
