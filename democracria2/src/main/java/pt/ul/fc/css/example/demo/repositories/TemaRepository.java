package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {
  @Query("SELECT a FROM Tema a")
  List<Tema> getList();

  @Query("SELECT t FROM Tema t WHERE t.id = :id")
  Tema findByID(@Param("id") Long id);
}
