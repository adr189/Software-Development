package pt.ul.fc.css.example.demo.handlers;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Scanner;
import pt.ul.fc.css.example.demo.Aprovacao;
import pt.ul.fc.css.example.demo.entities.Cidadao;
import pt.ul.fc.css.example.demo.entities.Delegado;
import pt.ul.fc.css.example.demo.entities.DelegadoProposta;
import pt.ul.fc.css.example.demo.entities.Proposta;
import pt.ul.fc.css.example.demo.repositories.DelegadoPropostaRepository;
import pt.ul.fc.css.example.demo.repositories.PropostaRepository;

public class VotarPropostaHandler {
  private final PropostaRepository propostaRepository;

  private final DelegadoPropostaRepository delegadoPropostaRepository;

  public VotarPropostaHandler(
      PropostaRepository propostaRepository,
      DelegadoPropostaRepository delegadoPropostaRepository) {
    this.propostaRepository = propostaRepository;
    this.delegadoPropostaRepository = delegadoPropostaRepository;
  }

  @Transactional
  public Proposta votarNumaProposta(Cidadao cidadao_atual) throws Exception {
    try {

      List<Proposta> validProps = this.propostaRepository.getListByStatus(Aprovacao.EM_CURSO);
      if (validProps.size() > 0) {
        System.out.println("A propostas ativas, são:");
        for (int i = 0; i < validProps.size(); i++) {
          System.out.println(
              "ID: " + validProps.get(i).getId() + "\nTitulo:" + validProps.get(i).getTitulo());
        }
        System.out.println("Qual o ID da proposta que pretende votar?");
        Scanner scanner = new Scanner(System.in);
        int idProp = Integer.parseInt(scanner.nextLine());
        Proposta propostaCorrente = this.propostaRepository.findByID(Long.valueOf(idProp));

        Delegado dd = cidadao_atual.getDelegadoDoTema(propostaCorrente.getTema());
        if (dd == null) {
          System.out.println(
              "Nao Possui nenhum delegado para com um tema relacionado ao da proposta.");
          System.out.println("Deseja Votar? [S/N]");
          String decisao = scanner.nextLine();
          if (decisao.equalsIgnoreCase("S")) {
            System.out.println("Vota a favor? [S/N]");
            String decisao2 = scanner.nextLine();
            if (decisao2.equalsIgnoreCase("S")) {
              if (cidadao_atual instanceof Delegado) {
                propostaCorrente.indicarVotoDelegado((Delegado) cidadao_atual, true);
              } else {
                propostaCorrente.indicarVoto(cidadao_atual, true);
              }
            } else {
              if (cidadao_atual instanceof Delegado) {
                propostaCorrente.indicarVotoDelegado((Delegado) cidadao_atual, false);
              } else {
                propostaCorrente.indicarVoto(cidadao_atual, false);
              }
            }
          }
          propostaRepository.save(propostaCorrente);
          scanner.close();
          return propostaCorrente;
        }

        DelegadoProposta a =
            delegadoPropostaRepository.getDecisaoByDelegadoProposta(propostaCorrente, dd);

        if (a.getFlag()) {
          System.out.println("Caso não vote o seu voto por Omissão é a aprovação.");
          System.out.println("Pretende alterar o voto? [S/N]");
          String decisao = scanner.nextLine();

          if (decisao.equalsIgnoreCase("S")) {
            if (cidadao_atual instanceof Delegado) {
              propostaCorrente.indicarVotoDelegado((Delegado) cidadao_atual, false);
            } else {
              propostaCorrente.indicarVoto(cidadao_atual, false);
            }
          }
        } else {
          System.out.println("Caso não vote o seu voto por Omissão é a reprovação.");
          System.out.println("Pretende alterar o voto? [S/N]");
          String decisao = scanner.nextLine();
          if (decisao.equalsIgnoreCase("S")) {
            if (cidadao_atual instanceof Delegado) {
              propostaCorrente.indicarVotoDelegado((Delegado) cidadao_atual, true);
            } else {
              propostaCorrente.indicarVoto(cidadao_atual, true);
            }
          }
        }
        propostaRepository.save(propostaCorrente);
        scanner.close();
        return propostaCorrente;
      }
    } catch (Exception e) {
      throw new Exception("Erro ao votar na proposta", e);
    }

    return null;
  }

  public Proposta votarNumaProposta2(Cidadao cidadao_atual, Proposta prop, Boolean decisao)
      throws Exception {
    prop.indicarVoto(cidadao_atual, decisao);
    return prop;
  }
}
