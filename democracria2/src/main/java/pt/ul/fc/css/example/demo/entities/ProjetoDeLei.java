package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.*;
import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.lang.NonNull;

@Entity
public class ProjetoDeLei {

  public static final String FIND_ALL = "ProjetoDeLei.findAll";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull private String titulo;

  @Lob private File PDF;

  @Temporal(TemporalType.DATE)
  private Date dataEmissao;

  @Temporal(TemporalType.DATE)
  private Date dataDeExpiracao;

  private int apoio;

  @NonNull private String textoDescritivo;

  // Se for true -> expirado, false -> não expirado
  @NonNull private boolean estado;

  @ManyToMany private List<Cidadao> listaDeApoiantes;

  @ManyToOne @NonNull private Delegado proposto;

  @OneToOne @NonNull private Tema tema;

  public ProjetoDeLei(
      String titulo,
      File PDF,
      Date dataEmissao,
      Date dataDeExpiracao,
      String textoDescritivo,
      Delegado delegado,
      Tema t) {
    this.titulo = titulo;
    this.PDF = PDF;
    this.dataEmissao = dataEmissao;
    this.dataDeExpiracao = dataDeExpiracao;
    this.apoio = 0;
    this.textoDescritivo = textoDescritivo;
    this.listaDeApoiantes = new ArrayList<>();
    this.proposto = delegado;
    this.tema = t;

    LocalDateTime localDateTime = LocalDateTime.now(); // current local date and time

    Instant startInstant = dataEmissao.toInstant();
    Instant endInstant = dataDeExpiracao.toInstant();
    Instant localInstant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

    boolean isBetween = localInstant.isAfter(startInstant) && localInstant.isBefore(endInstant);

    if (isBetween) {
      this.estado = true; // projeto dentro da data
    } else {
      this.estado = false; // projeto fora da data
    }
  }

  public ProjetoDeLei() {}

  @NonNull
  public Tema getTema() {
    return tema;
  }

  public void setTema(@NonNull Tema tema) {
    this.tema = tema;
  }

  @NonNull
  public Delegado getProposto() {
    return proposto;
  }

  public void setProposto(@NonNull Delegado proposto) {
    this.proposto = proposto;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public File getPDF() {
    return PDF;
  }

  public void setPDF(File pDF) {
    PDF = pDF;
  }

  public Date getDataEmissao() {
    return dataEmissao;
  }

  public void setDataEmissao(Date dataEmissao) {
    this.dataEmissao = dataEmissao;
  }

  public Date getDataDeExpiracao() {
    return dataDeExpiracao;
  }

  public void setDataDeExpiracao(Date dataDeExpiracao) {
    this.dataDeExpiracao = dataDeExpiracao;
  }

  public int getApoio() {
    return apoio;
  }

  public void setApoio(int apoio) {
    this.apoio = apoio;
  }

  public String getTextoDescritivo() {
    return textoDescritivo;
  }

  public void setTextoDescritivo(String textoDescritivo) {
    this.textoDescritivo = textoDescritivo;
  }

  public boolean isEstado() {
    return estado;
  }

  public void setEstado(boolean estado) {
    this.estado = estado;
  }

  public List<Cidadao> getListaDeApoiantes() {
    return listaDeApoiantes;
  }

  public void setListaDeApoiantes(List<Cidadao> listaDeApoiantes) {
    this.listaDeApoiantes = listaDeApoiantes;
  }

  public boolean expirado(Date data) {
    if (this.dataEmissao.before(data) && this.dataDeExpiracao.after(data)) {
      return false;
    }
    return true;
  }

  public boolean naoExpirado(Date data) {
    if (this.dataEmissao.before(data) && this.dataDeExpiracao.after(data)) {
      return true;
    }
    return false;
  }

  public boolean adicionarApoio(Cidadao c) {
    if (listaDeApoiantes.contains(c)) {
      return false;
    }
    listaDeApoiantes.add(c);
    apoio++;
    return true;
  }

  public boolean temApoiosSuficientes() {
    return apoio > 10000;
  }
}
