package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.Cidadao;
import pt.ul.fc.css.example.demo.entities.Delegado;

public interface CidadaoRepository extends JpaRepository<Cidadao, Long> {

  @Query("SELECT a FROM Cidadao a")
  List<Cidadao> getList();

  @Query("SELECT c FROM Cidadao c WHERE c.dtype = Delegado ")
  List<Delegado> getListDelegado();

  @Query("SELECT c FROM Cidadao c WHERE c.dtype = Cidadao ")
  List<Cidadao> getListCidadao();

  @Query("SELECT c FROM Cidadao c WHERE c.id = :id")
  Cidadao findByID(@Param("id") Long id);

  @Query("SELECT c FROM Cidadao c WHERE c.numero_de_cc LIKE %:q% ")
  Cidadao findByCC(@Param("q") String q);
}
