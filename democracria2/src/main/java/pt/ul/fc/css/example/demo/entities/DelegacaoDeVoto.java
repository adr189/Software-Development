package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.springframework.lang.NonNull;

@Entity
public class DelegacaoDeVoto {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull @ManyToOne private Cidadao cidadao;

  @NonNull @ManyToOne private Delegado delegado;

  @ManyToOne @NonNull private Tema tema;

  public DelegacaoDeVoto(
      @NonNull Cidadao cidadao, @NonNull Delegado delegado, @NonNull Tema temaDelegado) {
    this.cidadao = cidadao;
    this.delegado = delegado;
    this.tema = temaDelegado;
  }

  public DelegacaoDeVoto() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @NonNull
  public Cidadao getCidadao() {
    return cidadao;
  }

  public void setCidadao(@NonNull Cidadao cidadao) {
    this.cidadao = cidadao;
  }

  @NonNull
  public Delegado getDelegado() {
    return delegado;
  }

  public void setDelegado(@NonNull Delegado delegado) {
    this.delegado = delegado;
  }

  @NonNull
  public Tema getTemaDelegado() {
    return tema;
  }

  public void setTemaDelegado(@NonNull Tema temaDelegado) {
    this.tema = temaDelegado;
  }

  @NonNull
  public Tema getTema() {
    return tema;
  }

  public void setTema(@NonNull Tema tema) {
    this.tema = tema;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (DelegacaoDeVoto) obj;
    return Objects.equals(this.id, that.id)
        && Objects.equals(this.cidadao, that.cidadao)
        && Objects.equals(this.delegado, that.delegado)
        && Objects.equals(this.tema, that.tema);
  }
}
