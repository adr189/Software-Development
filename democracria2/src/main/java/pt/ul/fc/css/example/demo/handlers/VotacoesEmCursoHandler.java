package pt.ul.fc.css.example.demo.handlers;

import java.util.List;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.Aprovacao;
import pt.ul.fc.css.example.demo.entities.Proposta;
import pt.ul.fc.css.example.demo.repositories.PropostaRepository;

@Component
public class VotacoesEmCursoHandler {
  PropostaRepository pl;

  public VotacoesEmCursoHandler(PropostaRepository pl) {
    this.pl = pl;
  }

  public List<Proposta> pedirListaDeVotacoes(Aprovacao status) {
    return pl.getListByStatus(status);
  }
}
