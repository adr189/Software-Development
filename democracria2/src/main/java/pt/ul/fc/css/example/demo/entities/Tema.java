package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.*;
import java.util.List;
import org.springframework.lang.NonNull;

@Entity
public class Tema {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull private String nome;
  @ManyToOne private Tema parent;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
  private List<Tema> subTemas;

  @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
  private List<DelegacaoDeVoto> listaDeDelegacoes;

  @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
  private List<Proposta> listaDePropostas;

  @NonNull
  @Column(unique = true)
  public String getNome() {
    return nome;
  }

  public void setNome(@NonNull String nome) {
    this.nome = nome;
  }

  public Tema() {}

  public Tema(@NonNull String nome) {
    this.nome = nome;
  }

  public List<Proposta> getListaDePropostas() {
    return listaDePropostas;
  }

  public void setListaDePropostas(List<Proposta> listaDePropostas) {
    this.listaDePropostas = listaDePropostas;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Tema getParent() {
    return parent;
  }

  public void setParent(Tema parent) {
    this.parent = parent;
  }

  public List<Tema> getSubTemas() {
    return subTemas;
  }

  public void setSubTemas(List<Tema> subTemas) {
    this.subTemas = subTemas;
  }

  public List<DelegacaoDeVoto> getListaDeDelegacoes() {
    return listaDeDelegacoes;
  }

  public void setListaDeDelegacoes(List<DelegacaoDeVoto> listaDeDelegacoes) {
    this.listaDeDelegacoes = listaDeDelegacoes;
  }
}
