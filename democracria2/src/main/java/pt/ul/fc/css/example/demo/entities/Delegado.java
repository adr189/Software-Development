package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.lang.NonNull;

@Entity
public class Delegado extends Cidadao {
  @OneToMany(mappedBy = "delegado", cascade = CascadeType.ALL)
  private List<DelegacaoDeVoto> lista_eleitores;

  @ManyToMany(mappedBy = "relacaoDelegadoDecisao", cascade = CascadeType.ALL)
  private List<Proposta> listaPropostas;

  @OneToMany(mappedBy = "proposto", cascade = CascadeType.ALL)
  private List<ProjetoDeLei> listaProjetos;

  @OneToMany(mappedBy = "delegadoQueCriou", cascade = CascadeType.ALL)
  private List<Proposta> listaPropostasCriadas;

  @OneToMany(mappedBy = "delegado", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DelegadoProposta> propostas = new ArrayList<>();

  public Delegado() {}

  public Delegado(@NonNull String numero_de_cc, @NonNull String nome, String token_autenticacao) {
    super(numero_de_cc, nome, token_autenticacao);
  }

  public List<DelegacaoDeVoto> getListaEleitores() {
    return lista_eleitores;
  }

  public List<DelegacaoDeVoto> getLista_eleitores() {
    return lista_eleitores;
  }

  public void setLista_eleitores(List<DelegacaoDeVoto> lista_eleitores) {
    this.lista_eleitores = lista_eleitores;
  }

  public List<Proposta> getListaPropostas() {
    return listaPropostas;
  }

  public void setListaPropostas(List<Proposta> listaPropostas) {
    this.listaPropostas = listaPropostas;
  }

  public List<ProjetoDeLei> getListaProjetos() {
    return listaProjetos;
  }

  public void setListaProjetos(List<ProjetoDeLei> listaProjetos) {
    this.listaProjetos = listaProjetos;
  }

  public List<Proposta> getListaPropostasCriadas() {
    return listaPropostasCriadas;
  }

  public void setListaPropostasCriadas(List<Proposta> listaPropostasCriadas) {
    this.listaPropostasCriadas = listaPropostasCriadas;
  }

  public List<DelegadoProposta> getPropostas() {
    return propostas;
  }

  public void setPropostas(List<DelegadoProposta> propostas) {
    this.propostas = propostas;
  }
}
