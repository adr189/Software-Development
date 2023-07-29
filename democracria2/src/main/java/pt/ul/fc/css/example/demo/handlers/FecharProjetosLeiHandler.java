package pt.ul.fc.css.example.demo.handlers;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.ProjetoDeLei;
import pt.ul.fc.css.example.demo.repositories.ProjetoDeLeiRepository;

@Component
public class FecharProjetosLeiHandler {

  private ProjetoDeLeiRepository pdlr;

  public FecharProjetosLeiHandler(ProjetoDeLeiRepository pdlr) {
    this.pdlr = pdlr;
  }

  // Caso de uso executa a cada 3 segundos
  @Scheduled(fixedDelay = 3000)
  public List<ProjetoDeLei> fecharProjetosDeLei(Date data) throws InterruptedException {
    List<ProjetoDeLei> listaProjetosDeLei = pdlr.getList();
    for (ProjetoDeLei pdl : listaProjetosDeLei) {
      if (pdl.expirado(data)) {
        pdl.setEstado(true);
        pdlr.save(pdl);
      }
    }
    System.out.println("Começando - " + LocalTime.now());
    Thread.sleep(1000);
    System.out.println("Terminando - " + LocalTime.now());
    return listaProjetosDeLei;
  }
}
