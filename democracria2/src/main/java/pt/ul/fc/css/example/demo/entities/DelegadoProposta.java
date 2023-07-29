package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.*;

@Entity
public class DelegadoProposta {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne private Proposta proposta;

  @ManyToOne private Delegado delegado;

  @Column(name = "flag")
  private boolean flag;

  public DelegadoProposta() {}

  public DelegadoProposta(Proposta proposta, Delegado delegado, boolean flag) {
    this.proposta = proposta;
    this.delegado = delegado;
    this.flag = flag;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Proposta getProposta() {
    return proposta;
  }

  public void setProposta(Proposta proposta) {
    this.proposta = proposta;
  }

  public Delegado getDelegado() {
    return delegado;
  }

  public void setDelegado(Delegado delegado) {
    this.delegado = delegado;
  }

  public boolean getFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }
}
