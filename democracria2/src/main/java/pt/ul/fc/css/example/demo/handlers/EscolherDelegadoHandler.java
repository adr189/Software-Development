package pt.ul.fc.css.example.demo.handlers;

// import pt.ul.fc.css.example.demo.catalogs.CidadaoCatalog;

import pt.ul.fc.css.example.demo.ApplicationException;
import pt.ul.fc.css.example.demo.entities.Cidadao;
import pt.ul.fc.css.example.demo.entities.DelegacaoDeVoto;
import pt.ul.fc.css.example.demo.entities.Delegado;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.repositories.CidadaoRepository;
import pt.ul.fc.css.example.demo.repositories.DelegacaoDeVotoRepository;

public class EscolherDelegadoHandler {

  private CidadaoRepository cr;
  private DelegacaoDeVotoRepository dvr;

  public EscolherDelegadoHandler(CidadaoRepository cr, DelegacaoDeVotoRepository dvr) {
    this.cr = cr;
    this.dvr = dvr;
  }

  public DelegacaoDeVoto escolherDelegado(Cidadao c, Tema t, Delegado d)
      throws ApplicationException {
    DelegacaoDeVoto delegacao = c.addCidadaoEscolherDelegado(t, d);
    dvr.save(delegacao);
    return delegacao;
  }
}
