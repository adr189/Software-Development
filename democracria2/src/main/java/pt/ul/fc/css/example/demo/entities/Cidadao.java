package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.lang.NonNull;

@Entity
public class Cidadao {

  @Column(insertable = false, updatable = false)
  private String dtype;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull private String numero_de_cc;

  @NonNull private String nome;
  private String token_autenticacao;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "cidadao", cascade = CascadeType.ALL)
  private List<DelegacaoDeVoto> listaDeDelegacoes;

  @ManyToMany(mappedBy = "listaDePessoasVotos", cascade = CascadeType.ALL)
  private List<Proposta> listaPropostas;

  @ManyToMany(mappedBy = "listaDeApoiantes", cascade = CascadeType.ALL)
  private List<ProjetoDeLei> listaDeApoios;

  public Cidadao() {}

  public Cidadao(@NonNull String numero_de_cc, @NonNull String nome, String token_autenticacao) {
    this.numero_de_cc = numero_de_cc;
    this.nome = nome;
    this.token_autenticacao = token_autenticacao;
    this.listaDeDelegacoes = new ArrayList<DelegacaoDeVoto>();
  }

  public Long getId() {
    return id;
  }

  public String getNumero_de_cc() {
    return numero_de_cc;
  }

  @NonNull
  public String getNome() {
    return nome;
  }

  public String getToken_autenticacao() {
    return token_autenticacao;
  }

  public void setToken_autenticacao(String token_autenticacao) {
    this.token_autenticacao = token_autenticacao;
  }

  public void setNome(@NonNull String nome) {
    this.nome = nome;
  }

  public void setNumero_de_cc(@NonNull String numero_de_cc) {
    this.numero_de_cc = numero_de_cc;
  }

  public String getDtype() {
    return dtype;
  }

  public void setDtype(String dtype) {
    this.dtype = dtype;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<DelegacaoDeVoto> getListaDeDelegacoes() {
    return listaDeDelegacoes;
  }

  public void setListaDeDelegacoes(List<DelegacaoDeVoto> listaDeDelegacoes) {
    this.listaDeDelegacoes = listaDeDelegacoes;
  }

  public List<Proposta> getListaPropostas() {
    return listaPropostas;
  }

  public void setListaPropostas(List<Proposta> listaPropostas) {
    this.listaPropostas = listaPropostas;
  }

  public List<ProjetoDeLei> getListaDeApoios() {
    return listaDeApoios;
  }

  public void setListaDeApoios(List<ProjetoDeLei> listaDeApoios) {
    this.listaDeApoios = listaDeApoios;
  }

  public void escolherDelegado(Tema t, @NonNull Delegado d) {
    listaDeDelegacoes.add(new DelegacaoDeVoto(this, d, t));
  }

  public Delegado getDelegadoDoTema(Tema t) {
    Tema current = t;
    while (current != null) {
      for (DelegacaoDeVoto d : listaDeDelegacoes) {
        if (d.getTemaDelegado().getNome().equalsIgnoreCase(current.getNome()))
          return d.getDelegado();
      }
      current = current.getParent();
    }
    return null;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Cidadao) obj;
    return Objects.equals(this.id, that.id)
        && Objects.equals(this.nome, that.nome)
        && Objects.equals(this.numero_de_cc, that.numero_de_cc)
        && Objects.equals(this.token_autenticacao, that.token_autenticacao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numero_de_cc, nome, token_autenticacao);
  }

  @Override
  public String toString() {
    return "Author["
        + "numero_de_cc="
        + numero_de_cc
        + ", "
        + "nome="
        + nome
        + ", "
        + "token_autenticacao="
        + token_autenticacao
        + ']';
  }

  public DelegacaoDeVoto addCidadaoEscolherDelegado(Tema t, Delegado d) {
    DelegacaoDeVoto dv = new DelegacaoDeVoto(this, d, t);
    if (listaDeDelegacoes.contains(dv)) { // esse delegado ja foi escolhido
      return null;
    }
    listaDeDelegacoes.add(dv);
    return dv;
  }
}
