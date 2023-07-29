package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.*;

public interface DelegadoPropostaRepository extends JpaRepository<DelegadoProposta, Long> {
  @Query("SELECT l FROM ProjetoDeLei l")
  List<DelegadoProposta> getList();

  @Query("SELECT dl FROM DelegadoProposta dl WHERE dl.id = :id")
  DelegadoProposta findByID(@Param("id") Long id);

  @Query(
      "SELECT dl FROM DelegadoProposta dl WHERE dl.proposta = :proposta AND dl.delegado = :delegado")
  DelegadoProposta getDecisaoByDelegadoProposta(
      @Param("proposta") Proposta propostaCorrente, @Param("delegado") Delegado dd);
}
