package pt.ul.fc.css.example.demo.handlers;

import pt.ul.fc.css.example.demo.entities.Cidadao;
import pt.ul.fc.css.example.demo.entities.ProjetoDeLei;
import pt.ul.fc.css.example.demo.entities.Proposta;
import pt.ul.fc.css.example.demo.repositories.ProjetoDeLeiRepository;
import pt.ul.fc.css.example.demo.repositories.PropostaRepository;

public class ApoiarProjetoHandler {

  private ProjetoDeLeiRepository plr;
  private PropostaRepository pr;

  public ApoiarProjetoHandler(ProjetoDeLeiRepository plr, PropostaRepository pr) {
    this.plr = plr;
    this.pr = pr;
  }

  public Long apoiarprojeto(ProjetoDeLei p, Cidadao c) {
    if (p.adicionarApoio(c)) {
      if (p.temApoiosSuficientes()) {
        Proposta prop = new Proposta(p);
        pr.save(prop);
        plr.delete(p);
        return prop.getId();
      }
      plr.save(p);
      return p.getId();
    } else {
      // O CIDADAO JA TINHA VOTADO
    }
    return p.getId();
  }
}
