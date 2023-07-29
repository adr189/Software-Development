package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.DelegacaoDeVoto;

// NAO SEI SE É NECESSARIO!!!
public interface DelegacaoDeVotoRepository extends JpaRepository<DelegacaoDeVoto, Long> {
  @Query("SELECT a FROM DelegacaoDeVoto a")
  List<DelegacaoDeVoto> getList();

  @Query("SELECT dv FROM DelegacaoDeVoto dv WHERE dv.id = :id")
  DelegacaoDeVoto findByID(@Param("id") Long id);
}
