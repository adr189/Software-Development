package pt.ul.fc.css.example.demo.handlers;

import java.io.File;
import java.util.Date;
import pt.ul.fc.css.example.demo.ApplicationException;
import pt.ul.fc.css.example.demo.entities.Delegado;
import pt.ul.fc.css.example.demo.entities.ProjetoDeLei;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.repositories.ProjetoDeLeiRepository;

public class ApresentarProjetoLeiHandler {

  private ProjetoDeLeiRepository pdlr;

  public ApresentarProjetoLeiHandler(ProjetoDeLeiRepository pdlr) {
    this.pdlr = pdlr;
  }

  public ProjetoDeLei proporProjetoDeLei(
      String titulo,
      String textoDescritivo,
      File PDF,
      Date dataEmissao,
      Date dataDeExpiracao,
      Tema tema,
      Delegado d)
      throws ApplicationException {
    ProjetoDeLei pl =
        new ProjetoDeLei(titulo, PDF, dataEmissao, dataDeExpiracao, textoDescritivo, d, tema);
    pdlr.save(pl);
    return pl;
  }
}
