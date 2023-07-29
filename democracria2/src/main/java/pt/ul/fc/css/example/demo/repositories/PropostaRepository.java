package pt.ul.fc.css.example.demo.repositories;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.Aprovacao;
import pt.ul.fc.css.example.demo.entities.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
  @Query("SELECT p FROM Proposta p")
  @Transactional
  List<Proposta> getList();

  @Query("SELECT p FROM Proposta p WHERE p.id = :id")
  @Transactional
  Proposta findByID(@Param("id") Long id);

  @Query("SELECT p FROM Proposta p WHERE p.aprovacao = :status")
  @Transactional
  List<Proposta> getListByStatus(@Param("status") Aprovacao status);
}
