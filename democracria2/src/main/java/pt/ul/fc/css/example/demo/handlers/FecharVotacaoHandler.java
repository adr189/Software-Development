package pt.ul.fc.css.example.demo.handlers;

import java.time.LocalTime;
import java.util.Date;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;

import pt.ul.fc.css.example.demo.ApplicationException;
import pt.ul.fc.css.example.demo.entities.Proposta;
import pt.ul.fc.css.example.demo.repositories.CidadaoRepository;
import pt.ul.fc.css.example.demo.repositories.PropostaRepository;

public class FecharVotacaoHandler {

  private CidadaoRepository cidadoes;

  private PropostaRepository pr;

  public FecharVotacaoHandler(CidadaoRepository cidRep) {
    this.cidadoes = cidRep;
  }

  public void fecharVotacao(Date data, Proposta pr) throws ApplicationException {
    if (!(pr.expirado(data))) {
      throw new ApplicationException(
          "A(s) votação(ões) não podem ser fechados porque ainda não expiraram");
    }
    try {
      pr.fecharProposta(this.cidadoes.getList());
    } catch (Exception e) {
      throw new ApplicationException("Erro ao fechar a Proposta", e);
    }
  }

  @Scheduled(fixedDelay = 3000)
  public void fecharVotacoes(Date data) throws ApplicationException, InterruptedException {
    List<Proposta> listaPropostas = pr.getList();
    for (Proposta pr : listaPropostas) {
      if (!(pr.expirado(data))) {
        throw new ApplicationException(
            "A(s) votação(ões) não podem ser fechados porque ainda não expiraram");
      }
      try {
        pr.fecharProposta(this.cidadoes.getList());
      } catch (Exception e) {
        throw new ApplicationException("Erro ao fechar a Proposta", e);
      }
    }
    System.out.println("Começando - " + LocalTime.now());
    Thread.sleep(1000);
    System.out.println("Terminando - " + LocalTime.now());
  }
}
