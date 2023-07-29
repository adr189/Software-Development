package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.*;
import java.io.File;
import java.util.*;
import org.springframework.lang.NonNull;
import pt.ul.fc.css.example.demo.Aprovacao;

@Entity
public class Proposta {
  public static final String FIND_ALL = "Proposta.findAll";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private int[] votosComContra;

  @NonNull private String titulo;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Cidadao> listaDePessoasVotos = new ArrayList<>();

  @NonNull private Aprovacao aprovacao;

  @Temporal(TemporalType.DATE)
  private Date dataEmissao;

  @Temporal(TemporalType.DATE)
  private Date dataExpiracao;

  @Lob private File PDF;

  @ManyToOne @NonNull private Delegado delegadoQueCriou;

  @OneToMany(
    fetch = FetchType.EAGER,
    mappedBy = "proposta",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<DelegadoProposta> relacaoDelegadoDecisao = new ArrayList<>();

  @ManyToOne private Tema tema;

  public Proposta() {}

  public Proposta(ProjetoDeLei p) {
    this.votosComContra = new int[2];
    this.titulo = p.getTitulo();
    this.dataEmissao = p.getDataEmissao();

    Date dataDeExpiracao = p.getDataDeExpiracao();

    long diffInMillis = dataEmissao.getTime() - dataDeExpiracao.getTime();
    long diffInSeconds = diffInMillis / 1000;

    if (diffInSeconds < 15 * 24 * 60 * 60 || diffInSeconds > 60 * 24 * 60 * 60) {
      int daysToAdd = 30; // Se o projeto tiver menos de 15 dias de prazo ou mais de 2 meses,
      // entao por como default 1 mes de prazo
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(dataEmissao);
      calendar.add(Calendar.DATE, daysToAdd);
      this.dataExpiracao = calendar.getTime();
    } else {
      this.dataExpiracao = dataDeExpiracao;
    }
    this.votosComContra[0] = 1;

    this.relacaoDelegadoDecisao.add(new DelegadoProposta(this, p.getProposto(), true));

    this.delegadoQueCriou = p.getProposto();
    this.PDF = p.getPDF();
    this.aprovacao = Aprovacao.EM_CURSO;
    this.tema = p.getTema();
  }

  public Proposta(
      int[] votosComContra,
      String titulo,
      List<Cidadao> listaDePessoasVotos,
      Date dataEmissao,
      Date dataExpiracao,
      File pDF,
      //      Map<Delegado, Boolean> relacaoDelegadoDecisao,
      List<DelegadoProposta> relacaoDelegadoDecisao,
      Tema tema) {
    this.votosComContra = votosComContra;
    this.titulo = titulo;
    this.listaDePessoasVotos = listaDePessoasVotos;
    this.dataEmissao = dataEmissao;
    this.dataExpiracao = dataExpiracao;
    this.PDF = pDF;
    this.relacaoDelegadoDecisao = relacaoDelegadoDecisao;
    this.tema = tema;
  }

  @NonNull
  public Delegado getDelegadoQueCriou() {
    return delegadoQueCriou;
  }

  public void setDelegadoQueCriou(@NonNull Delegado delegadoQueCriou) {
    this.delegadoQueCriou = delegadoQueCriou;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int[] getVotosComContra() {
    return votosComContra;
  }

  public void setVotosComContra(int[] votosComContra) {
    this.votosComContra = votosComContra;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public List<Cidadao> getListaDePessoasVotos() {
    return listaDePessoasVotos;
  }

  public void setListaDePessoasVotos(List<Cidadao> listaDePessoasVotos) {
    this.listaDePessoasVotos = listaDePessoasVotos;
  }

  public Date getDataEmissao() {
    return dataEmissao;
  }

  public void setDataEmissao(Date dataEmissao) {
    this.dataEmissao = dataEmissao;
  }

  public Date getDataExpiracao() {
    return dataExpiracao;
  }

  public void setDataExpiracao(Date dataExpiracao) {
    this.dataExpiracao = dataExpiracao;
  }

  public File getPDF() {
    return PDF;
  }

  public void setPDF(File pDF) {
    PDF = pDF;
  }

  public Aprovacao getAprovacao() {
    return aprovacao;
  }

  public void setAprovacao(Aprovacao aprovacao) {
    this.aprovacao = aprovacao;
  }

  public List<DelegadoProposta> getRelacaoDelegadoDecisao() {
    return relacaoDelegadoDecisao;
  }

  public void setRelacaoDelegadoDecisao(List<DelegadoProposta> relacaoDelegadoDecisao) {
    this.relacaoDelegadoDecisao = relacaoDelegadoDecisao;
  }

  public void setTema(Tema tm) {
    this.tema = tm;
  }

  public Tema getTema() {
    return this.tema;
  }

  public boolean expirado(Date data) {
    if (this.dataExpiracao.before(data)) {
      return true;
    } else {
      return false;
    }
  }

  public Aprovacao contagemEDecisao() {
    if (this.votosComContra[0] < this.votosComContra[1]) { // E se for empate
      this.setAprovacao(aprovacao.REJEITADA);
    } else if (this.votosComContra[0] > this.votosComContra[1]) {
      this.setAprovacao(aprovacao.APROVADA);
    }
    return this.aprovacao;
  }

  public void indicarVoto(Cidadao c, Boolean decisao) {
    this.listaDePessoasVotos.add(c);
    if (decisao) {
      this.votosComContra[0] += 1;
    } else {
      this.votosComContra[1] += 1;
    }
  }

  public void indicarVotoDelegado(Delegado d, Boolean decisao) {
    relacaoDelegadoDecisao.add(new DelegadoProposta(this, d, decisao));
    if (decisao) {
      this.votosComContra[0] += 1;
    } else {
      this.votosComContra[1] += 1;
    }
  }

  public void atribuirVotosDelegados(List<Cidadao> listacidadaos) {
    if (listacidadaos.removeAll(this.listaDePessoasVotos)) {
      for (int index = 0; index < listacidadaos.size(); index++) {
        Delegado delCorrente = listacidadaos.get(index).getDelegadoDoTema(this.tema);
        if (delCorrente == null) return; // Se o cidadao nao tiver votado e nao ter nenhum delegado
        if (listaDePessoasVotos.contains(listacidadaos.get(index))) { // Se ja tiver votado
          //          return;
        } else {

          for (DelegadoProposta dp : relacaoDelegadoDecisao) {
            if (dp.getDelegado().equals(delCorrente)) {
              this.indicarVoto(listacidadaos.get(index), dp.getFlag());
              //              return;
            }
          }
        }
      }
    }
  }

  public void fecharProposta(List<Cidadao> todosList) {
    this.atribuirVotosDelegados(todosList);
    this.setAprovacao(this.contagemEDecisao());
  }

  public boolean getDecisaoDelegado(Delegado delegado) {
    for (DelegadoProposta d : relacaoDelegadoDecisao) {
      if (d.getDelegado().equals(delegado)) {
        return d.getFlag();
      }
    }
    return false;
  }
}
