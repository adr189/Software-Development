package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.ProjetoDeLei;

public interface ProjetoDeLeiRepository extends JpaRepository<ProjetoDeLei, Long> {
  @Query("SELECT l FROM ProjetoDeLei l")
  List<ProjetoDeLei> getList();

  @Query("SELECT pl FROM ProjetoDeLei pl WHERE pl.id = :id")
  Optional<ProjetoDeLei> findById(@Param("id") Long id);
}
