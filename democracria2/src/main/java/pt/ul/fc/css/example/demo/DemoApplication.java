package pt.ul.fc.css.example.demo;

import jakarta.persistence.EntityManagerFactory;
import java.io.File;
import java.util.Date;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pt.ul.fc.css.example.demo.entities.*;
import pt.ul.fc.css.example.demo.handlers.ApoiarProjetoHandler;
import pt.ul.fc.css.example.demo.handlers.VotacoesEmCursoHandler;
import pt.ul.fc.css.example.demo.repositories.*;

@SpringBootApplication
public class DemoApplication {
  @Autowired EntityManagerFactory emf;
  //  @Autowired private ProjetoDeLeiRepository projetoDeLeiRepository;
  //  @Autowired private PropostaRepository propostaRepository;
  //  @Autowired private CidadaoRepository cidadaoRepository;
  //  @Autowired private DelegacaoDeVotoRepository delegacaoDeVotoRepository;
  //  @Autowired private TemaRepository temaRepository;
  //  @Autowired private DelegadoPropostaRepository delegadoPropostaRepository;
  private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(
      AuthorRepository repository,
      CidadaoRepository cidadaoRepository,
      DelegacaoDeVotoRepository delegacaoDeVotoRepository,
      ProjetoDeLeiRepository projetoDeLeiRepository,
      PropostaRepository propostaRepository,
      TemaRepository temaRepository,
      DelegadoPropostaRepository delegadoPropostaRepository) {

    return (args) -> {
      cidadaoRepository.deleteAll();
      temaRepository.deleteAll();
      projetoDeLeiRepository.deleteAll();
      delegadoPropostaRepository.deleteAll();
      propostaRepository.deleteAll();
      delegacaoDeVotoRepository.deleteAll();

      VotacoesEmCursoHandler v = new VotacoesEmCursoHandler(propostaRepository);

      Delegado d1 = new Delegado("999999999", "Joao", "bbbbb");
      cidadaoRepository.save(d1);
      Tema t1 = new Tema("Saude");
      temaRepository.save(t1);

      ProjetoDeLei projeto1 =
          new ProjetoDeLei(
              "Projetoteste",
              new File("Projetoteste.pdf"),
              new Date(),
              new Date(),
              "descricao do Projeto de teste",
              d1,
              t1);
      projetoDeLeiRepository.save(projeto1);

      ApoiarProjetoHandler aph =
          new ApoiarProjetoHandler(projetoDeLeiRepository, propostaRepository);
      Cidadao c1 = new Cidadao("123456789", "Bruno", "dnsa");
      cidadaoRepository.save(c1);
      Optional<ProjetoDeLei> mm = projetoDeLeiRepository.findById(Long.valueOf("1"));

      Cidadao c2 = new Cidadao("111111111", "Alexandre", "sd");
      cidadaoRepository.save(c2);
      Cidadao c3 = new Cidadao("222222222", "Rodrigo", "cvxc");
      cidadaoRepository.save(c3);
      Cidadao c4 = new Cidadao("333333333", "Joana", "ggbj");
      cidadaoRepository.save(c4);
      Cidadao c5 = new Cidadao("444444444", "Tiago", "bcas");
      cidadaoRepository.save(c5);
      Cidadao c6 = new Cidadao("555555555", "Maria", "klcx");
      cidadaoRepository.save(c6);

      ProjetoDeLei p1 =
          new ProjetoDeLei(
              "projeto1", new File("testepdf.txt"), new Date(), new Date(), "descricao1", d1, t1);
      Proposta proposta1 = new Proposta(p1);
      proposta1.setAprovacao(Aprovacao.EM_CURSO);

      ProjetoDeLei p2 =
          new ProjetoDeLei(
              "projeto2", new File("testepdf.txt"), new Date(), new Date(), "descricao2", d1, t1);
      Proposta proposta2 = new Proposta(p2);
      proposta2.setAprovacao(Aprovacao.REJEITADA);

      ProjetoDeLei p3 =
          new ProjetoDeLei(
              "projeto3", new File("testepdf.txt"), new Date(), new Date(), "descricao3", d1, t1);
      Proposta proposta3 = new Proposta(p3);
      proposta3.setAprovacao(Aprovacao.APROVADA);
      ProjetoDeLei p4 =
          new ProjetoDeLei(
              "projeto4",
              new File("projeto4descricao.pdf"),
              new Date(),
              new Date(),
              "descricao4",
              d1,
              t1);
      Proposta proposta4 = new Proposta(p4);
      proposta4.setAprovacao(Aprovacao.EM_CURSO);

      ProjetoDeLei p5 =
          new ProjetoDeLei(
              "projeto5",
              new File("projeto5descricao.pdf"),
              new Date(),
              new Date(),
              "descricao5",
              d1,
              t1);
      Proposta proposta5 = new Proposta(p5);
      proposta5.setAprovacao(Aprovacao.EM_CURSO);

      propostaRepository.save(proposta1);
      propostaRepository.save(proposta2);
      propostaRepository.save(proposta3);
      propostaRepository.save(proposta4);
      propostaRepository.save(proposta5);

      Delegado d2 = new Delegado("101010235", "Carla", "dsadb");
      cidadaoRepository.save(d2);
      Tema t2 = new Tema("Educacao");
      temaRepository.save(t2);

      ProjetoDeLei projeto2 =
          new ProjetoDeLei(
              "Escolas felizes",
              new File("EscolaFeliz.pdf"),
              new Date(),
              new Date(),
              "descricao do projeto escola",
              d2,
              t2);
      projetoDeLeiRepository.save(projeto2);

      //      // save a few customers
      //      repository.save(new Author("Jack", "Bauer"));
      //      repository.save(new Author("Chloe", "O'Brian"));
      //      repository.save(new Author("Kim", "Bauer"));
      //      repository.save(new Author("David", "Palmer"));
      //      repository.save(new Author("Michelle", "Dessler"));
      //
      //      // fetch all customers
      //      log.info("Customers found with findAll():");
      //      log.info("-------------------------------");
      //      for (Author author : repository.findAll()) {
      //        log.info(author.toString());
      //      }
      //      log.info("");
      //
      //      // fetch an individual customer by ID
      //      repository
      //          .findById(1L)
      //          .ifPresent(
      //              (Author author) -> {
      //                log.info("Customer found with findById(1L):");
      //                log.info("--------------------------------");
      //                log.info(author.toString());
      //                log.info("");
      //              });
      //
      //      // fetch customers by last name
      //      log.info("Author found with findByName('Bauer'):");
      //      log.info("--------------------------------------------");
      //      repository
      //          .findByName("Bauer")
      //          .forEach(
      //              bauer -> {
      //                log.info(bauer.toString());
      //              });
      //      // for (Customer bauer : repository.findByLastName("Bauer")) {
      //      // log.info(bauer.toString());
      //      // }
      //      log.info("");
    };
  }
}
