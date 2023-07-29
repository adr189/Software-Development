package pt.ul.fc.css.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ul.fc.css.example.demo.entities.*;
import pt.ul.fc.css.example.demo.handlers.*;
import pt.ul.fc.css.example.demo.repositories.*;

@SpringBootTest
class DemoApplicationTests {

  @Autowired private ProjetoDeLeiRepository projetoDeLeiRepository;
  @Autowired private PropostaRepository propostaRepository;
  @Autowired private CidadaoRepository cidadaoRepository;
  @Autowired private DelegacaoDeVotoRepository delegacaoDeVotoRepository;
  @Autowired private TemaRepository temaRepository;
  @Autowired private DelegadoPropostaRepository delegadoPropostaRepository;

  @Test
  void getProjetosDeLeiTest() {
    projetoDeLeiRepository.deleteAll();
    ProjetoDeLei proj1 = new ProjetoDeLei();
    ProjetoDeLei proj2 = new ProjetoDeLei();
    projetoDeLeiRepository.save(proj1);
    projetoDeLeiRepository.save(proj2);
    List<ProjetoDeLei> projetosDeLei = projetoDeLeiRepository.getList();
    assertThat(projetosDeLei).isNotNull();
    assertThat(projetosDeLei.size()).isEqualTo(2);
  }

  @Test
  void getProjetosDeLeiComParamsTest() {
    projetoDeLeiRepository.deleteAll();
    Delegado d1 = new Delegado("0000", "Luís Barros", "ASDFG");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Educação");
    temaRepository.save(t1);
    ProjetoDeLei proj1 =
        new ProjetoDeLei(
            "proj1", new File("proj1.pdf"), new Date(), new Date(), "Proj1 descricao", d1, t1);
    Delegado d2 = new Delegado("1111", "Alberto Martins", "QWERTY");
    cidadaoRepository.save(d2);
    Tema t2 = new Tema("Imigração");
    temaRepository.save(t2);
    ProjetoDeLei proj2 =
        new ProjetoDeLei(
            "proj2", new File("proj2.pdf"), new Date(), new Date(), "Proj2 descricao", d2, t2);
    projetoDeLeiRepository.save(proj1);
    projetoDeLeiRepository.save(proj2);
    List<ProjetoDeLei> projetosDeLei = projetoDeLeiRepository.getList();
    assertThat(projetosDeLei).isNotNull();
    assertThat(projetosDeLei.size()).isEqualTo(2);
  }

  @Test
  void getPropostasTest() {
    propostaRepository.deleteAll();
    Proposta prop1 = new Proposta();
    Proposta prop2 = new Proposta();
    propostaRepository.save(prop1);
    propostaRepository.save(prop2);
    List<Proposta> propostas = propostaRepository.getList();
    assertThat(propostas).isNotNull();
    assertThat(propostas.size()).isEqualTo(2);
  }

  @Test
  void getPropostasComParamsTest() {
    propostaRepository.deleteAll();
    Delegado d1 = new Delegado("0000", "Luís Barros", "ASDFG");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Educação");
    temaRepository.save(t1);
    ProjetoDeLei proj1 =
        new ProjetoDeLei(
            "proj1", new File("proj1.pdf"), new Date(), new Date(), "Proj1 descricao", d1, t1);
    Proposta prop1 = new Proposta(proj1);
    Delegado d2 = new Delegado("1111", "Alberto Martins", "QWERTY");
    cidadaoRepository.save(d2);
    Tema t2 = new Tema("Imigração");
    temaRepository.save(t2);
    ProjetoDeLei proj2 =
        new ProjetoDeLei(
            "proj2", new File("proj2.pdf"), new Date(), new Date(), "Proj2 descricao", d2, t2);
    Proposta prop2 = new Proposta(proj2);
    propostaRepository.save(prop1);
    propostaRepository.save(prop2);
    List<Proposta> propostas = propostaRepository.getList();
    assertThat(propostas).isNotNull();
    assertThat(propostas.size()).isEqualTo(2);
  }

  @Test
  public void getProjetoDeLeiByIdTest() {
    projetoDeLeiRepository.deleteAll();
    ProjetoDeLei proj = new ProjetoDeLei();
    projetoDeLeiRepository.save(proj);
    ProjetoDeLei projetoDeLeiPorId = projetoDeLeiRepository.findById(proj.getId()).get();
    assertThat(projetoDeLeiPorId).isNotNull();
  }

  @Test
  public void getProjetoDeLeiByIdComParamsTest() {
    projetoDeLeiRepository.deleteAll();
    Delegado d = new Delegado("0000", "Luís Barros", "ASDFG");
    cidadaoRepository.save(d);
    Tema t = new Tema("Educação");
    temaRepository.save(t);
    ProjetoDeLei proj =
        new ProjetoDeLei(
            "proj1", new File("proj1.pdf"), new Date(), new Date(), "Proj descricao", d, t);
    projetoDeLeiRepository.save(proj);
    ProjetoDeLei projetoDeLeiPorId = projetoDeLeiRepository.findById(proj.getId()).get();
    assertThat(projetoDeLeiPorId).isNotNull();
  }

  @Test
  public void getPropostaByIdTest() {
    propostaRepository.deleteAll();
    Proposta prop = new Proposta();
    propostaRepository.save(prop);
    Proposta propostaPorId = propostaRepository.findById(prop.getId()).get();
    assertThat(propostaPorId).isNotNull();
  }

  @Test
  public void getPropostaByIdComParamsTest() {
    propostaRepository.deleteAll();
    Delegado d = new Delegado("0000", "Luís Barros", "ASDFG");
    cidadaoRepository.save(d);
    Tema t = new Tema("Educação");
    temaRepository.save(t);
    ProjetoDeLei proj =
        new ProjetoDeLei(
            "proj1", new File("proj1.pdf"), new Date(), new Date(), "Proj descricao", d, t);
    Proposta prop = new Proposta(proj);
    propostaRepository.save(prop);
    Proposta propostaPorId = propostaRepository.findById(prop.getId()).get();
    assertThat(propostaPorId).isNotNull();
  }

  // Caso de uso E
  @Test
  public void apresentarProjetoTest() throws ApplicationException {
    projetoDeLeiRepository.deleteAll();
    ApresentarProjetoLeiHandler aplh = new ApresentarProjetoLeiHandler(projetoDeLeiRepository);
    Delegado d = new Delegado("0000", "Luís Barros", "ASDFG");
    cidadaoRepository.save(d);
    Tema t = new Tema("Educação");
    temaRepository.save(t);
    aplh.proporProjetoDeLei(
        "proj", "Proj descricao", new File("proj.pdf"), new Date(), new Date(), t, d);
    List<ProjetoDeLei> projetosDeLei = projetoDeLeiRepository.getList();
    assertThat(projetosDeLei).isNotNull();
    assertThat(projetosDeLei.size()).isEqualTo(1);
  }

  private Date haTrintaDias() {
    final Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -30);
    return calendar.getTime();
  }

  private Date ontem() {
    final Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -1);
    return calendar.getTime();
  }

  private Date daquiADois() {
    final Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, +2);
    return calendar.getTime();
  }

  // Caso de uso F
  @Test
  public void fecharProjetoTest() throws InterruptedException {
    projetoDeLeiRepository.deleteAll();
    FecharProjetosLeiHandler fplh = new FecharProjetosLeiHandler(projetoDeLeiRepository);
    Delegado d1 = new Delegado("0000", "Luís Barros", "ASDFG");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Educação");
    temaRepository.save(t1);
    ProjetoDeLei proj1 =
        new ProjetoDeLei(
            "proj1",
            new File("proj1.pdf"),
            haTrintaDias(),
            daquiADois(),
            "Proj1 descricao",
            d1,
            t1);
    Delegado d2 = new Delegado("1111", "Alberto Martins", "QWERTY");
    cidadaoRepository.save(d2);
    Tema t2 = new Tema("Imigração");
    temaRepository.save(t2);
    ProjetoDeLei proj2 =
        new ProjetoDeLei(
            "proj2",
            new File("proj2.pdf"),
            haTrintaDias(),
            daquiADois(),
            "Proj2 descricao",
            d2,
            t2);
    Delegado d3 = new Delegado("2222", "Carlos Almeida", "ZXCVB");
    cidadaoRepository.save(d3);
    Tema t3 = new Tema("Obras públicas");
    temaRepository.save(t3);
    ProjetoDeLei proj3 =
        new ProjetoDeLei(
            "proj3", new File("proj3.pdf"), haTrintaDias(), ontem(), "Proj3 descricao", d3, t3);
    projetoDeLeiRepository.save(proj1);
    projetoDeLeiRepository.save(proj2);
    projetoDeLeiRepository.save(proj3);
    List<ProjetoDeLei> projetosDeLei =
        fplh.fecharProjetosDeLei(new Date(System.currentTimeMillis()));
    List<ProjetoDeLei> projetosExpirados = new ArrayList<ProjetoDeLei>();
    for (ProjetoDeLei pdl : projetosDeLei) {
      if (pdl.expirado(new Date(System.currentTimeMillis()))) {
        projetosExpirados.add(pdl);
      }
    }
    assertThat(projetosExpirados).isNotNull();
    assertThat(projetosExpirados.size()).isEqualTo(1);
  }

  // Caso de uso G
  @Test
  public void consultarProjetosNaoExpiradosTest() {
    projetoDeLeiRepository.deleteAll();
    ConsultarProjetosLeiHandler cplh = new ConsultarProjetosLeiHandler(projetoDeLeiRepository);
    Delegado d1 = new Delegado("0000", "Luís Barros", "ASDFG");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Educação");
    temaRepository.save(t1);
    ProjetoDeLei proj1 =
        new ProjetoDeLei(
            "proj1", new File("proj1.pdf"), new Date(), new Date(), "Proj1 descricao", d1, t1);
    proj1.setEstado(false); // nao expirado
    Delegado d2 = new Delegado("1111", "Alberto Martins", "QWERTY");
    cidadaoRepository.save(d2);
    Tema t2 = new Tema("Imigração");
    temaRepository.save(t2);
    ProjetoDeLei proj2 =
        new ProjetoDeLei(
            "proj2", new File("proj2.pdf"), new Date(), new Date(), "Proj2 descricao", d2, t2);
    proj2.setEstado(true); // expirado
    Delegado d3 = new Delegado("2222", "Carlos Almeida", "ZXCVB");
    cidadaoRepository.save(d3);
    Tema t3 = new Tema("Obras públicas");
    temaRepository.save(t3);
    ProjetoDeLei proj3 =
        new ProjetoDeLei(
            "proj3", new File("proj3.pdf"), new Date(), new Date(), "Proj3 descricao", d3, t3);
    proj3.setEstado(false); // nao expirado
    projetoDeLeiRepository.save(proj1);
    projetoDeLeiRepository.save(proj2);
    projetoDeLeiRepository.save(proj3);
    List<ProjetoDeLei> projetosDeLei = cplh.consultarProjetosDeLeiNaoExpirados();
    assertThat(projetosDeLei).isNotNull();
    assertThat(projetosDeLei.size()).isEqualTo(2);
  }

  // CASO DE USO D
  @Test
  public void getVotacoesEmCursoTest() {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    propostaRepository.deleteAll();

    VotacoesEmCursoHandler v = new VotacoesEmCursoHandler(propostaRepository);

    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Saude");
    temaRepository.save(t1);
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

    propostaRepository.save(proposta1);
    propostaRepository.save(proposta2);
    propostaRepository.save(proposta3);

    List<Proposta> teste = v.pedirListaDeVotacoes(Aprovacao.EM_CURSO);

    assertThat(teste.size()).isEqualTo(1);
    assertThat(teste.get(0).getTitulo()).isEqualTo("projeto1");
  }

  // CASO DE USO H
  @Test
  public void getApoiarProjetoTest() {

    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();

    ApoiarProjetoHandler ap = new ApoiarProjetoHandler(projetoDeLeiRepository, propostaRepository);

    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);
    Cidadao c2 = new Cidadao("123456", "pedro", "9999");
    cidadaoRepository.save(c2);

    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Saude");
    temaRepository.save(t1);

    ProjetoDeLei p1 =
        new ProjetoDeLei(
            "projeto1", new File("testepdf.txt"), new Date(), new Date(), "descricao1", d1, t1);
    projetoDeLeiRepository.save(p1);

    ap.apoiarprojeto(p1, c1);
    List<ProjetoDeLei> teste = projetoDeLeiRepository.getList();
    assertThat(teste.get(0).getApoio()).isEqualTo(1);
  }

  @Test
  public void getPropostaDepoisDe10000Test() {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();
    propostaRepository.deleteAll();

    ApoiarProjetoHandler ap = new ApoiarProjetoHandler(projetoDeLeiRepository, propostaRepository);

    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);
    Cidadao c2 = new Cidadao("123456", "pedro", "9999");
    cidadaoRepository.save(c2);

    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Saude");
    temaRepository.save(t1);

    ProjetoDeLei p1 =
        new ProjetoDeLei(
            "projeto1", new File("testepdf.txt"), new Date(), new Date(), "descricao1", d1, t1);
    p1.setApoio(10000);
    projetoDeLeiRepository.save(p1);

    ap.apoiarprojeto(p1, c1);

    List<ProjetoDeLei> teste = projetoDeLeiRepository.getList();
    assertThat(teste.size()).isEqualTo(0);
    List<Proposta> teste2 = propostaRepository.getList();
    assertThat(teste2.size()).isEqualTo(1);
    assertThat(teste2.get(0).getTitulo()).isEqualTo("projeto1");
  }

  // CASO DE USO I
  @Test
  public void getEscolherDelegadoTest() throws ApplicationException {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();
    delegacaoDeVotoRepository.deleteAll();

    EscolherDelegadoHandler edH =
        new EscolherDelegadoHandler(cidadaoRepository, delegacaoDeVotoRepository);

    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);
    // Cidadao c2 = new Cidadao("123456", "pedro", "9999");
    // cidadaoRepository.save(c2);
    Tema t1 = new Tema("Saude");
    temaRepository.save(t1);
    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);

    delegacaoDeVotoRepository.save(edH.escolherDelegado(c1, t1, d1));

    List<DelegacaoDeVoto> listaDelegacao = delegacaoDeVotoRepository.getList();

    assertThat(listaDelegacao.get(0).getDelegado().getId()).isEqualTo(d1.getId());
    assertThat(listaDelegacao.get(0).getTemaDelegado().getId()).isEqualTo(t1.getId());
    assertThat(listaDelegacao.get(0).getCidadao().getId()).isEqualTo(c1.getId());
  }

  @Test
  void getTemasComParamsTest() {
    temaRepository.deleteAll();
    Tema tema1 = new Tema("Educação");
    Tema tema2 = new Tema("Imigração");
    temaRepository.save(tema1);
    temaRepository.save(tema2);
    List<Tema> temas = temaRepository.getList();
    assertThat(temas).isNotNull();
    assertThat(temas.size()).isEqualTo(2);
  }

  @Test
  public void getTemaByIdTest() {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();
    delegadoPropostaRepository.deleteAll();
    propostaRepository.deleteAll();
    delegacaoDeVotoRepository.deleteAll();

    Tema tema = new Tema();
    temaRepository.save(tema);
    Optional<Tema> temaPorId = temaRepository.findById(tema.getId());
    assertThat(temaPorId).isNotNull();
  }

  @Test
  void getDelegacoesTest() {
    delegacaoDeVotoRepository.deleteAll();
    DelegacaoDeVoto delegacao1 = new DelegacaoDeVoto();
    DelegacaoDeVoto delegacao2 = new DelegacaoDeVoto();
    delegacaoDeVotoRepository.save(delegacao1);
    delegacaoDeVotoRepository.save(delegacao2);
    List<DelegacaoDeVoto> delegacoes = delegacaoDeVotoRepository.getList();
    assertThat(delegacoes).isNotNull();
    assertThat(delegacoes.size()).isEqualTo(2);
  }

  @Test
  void getDelegacoesComParamsTest() {
    delegacaoDeVotoRepository.deleteAll();
    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);
    Cidadao c2 = new Cidadao("123456", "pedro", "9999");
    cidadaoRepository.save(c2);
    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);
    Delegado d2 = new Delegado("ll", "luis", "nnnnn");
    cidadaoRepository.save(d2);
    Tema t1 = new Tema("Educação");
    Tema t2 = new Tema("Imigração");
    temaRepository.save(t1);
    temaRepository.save(t2);
    DelegacaoDeVoto delegacao1 = new DelegacaoDeVoto(c1, d1, t1);
    DelegacaoDeVoto delegacao2 = new DelegacaoDeVoto(c2, d2, t2);
    delegacaoDeVotoRepository.save(delegacao1);
    delegacaoDeVotoRepository.save(delegacao2);
    List<DelegacaoDeVoto> delegacoes = delegacaoDeVotoRepository.getList();
    assertThat(delegacoes).isNotNull();
    assertThat(delegacoes.size()).isEqualTo(2);
  }

  @Test
  public void getDelegacaoByIdTest() {
    delegacaoDeVotoRepository.deleteAll();
    DelegacaoDeVoto delegacao = new DelegacaoDeVoto();
    delegacaoDeVotoRepository.save(delegacao);
    DelegacaoDeVoto delegacaoPorId = delegacaoDeVotoRepository.findById(delegacao.getId()).get();
    assertThat(delegacaoPorId).isNotNull();
  }

  @Test
  public void getDelegacaoByIdComParamsTest() {
    delegacaoDeVotoRepository.deleteAll();
    Cidadao c = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c);
    Delegado d = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d);
    Tema t = new Tema("Educação");
    temaRepository.save(t);
    DelegacaoDeVoto delegacao = new DelegacaoDeVoto(c, d, t);
    delegacaoDeVotoRepository.save(delegacao);
    DelegacaoDeVoto delegacaoPorId = delegacaoDeVotoRepository.findById(delegacao.getId()).get();
    assertThat(delegacaoPorId).isNotNull();
  }

  @Test
  public void getCidadaosTest() {
    cidadaoRepository.deleteAll();
    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);
    Cidadao c2 = new Cidadao("123456", "pedro", "9999");
    cidadaoRepository.save(c2);
    Cidadao c3 = new Cidadao("098765", "filipe", "5555");
    cidadaoRepository.save(c3);
    List<Cidadao> cidadaos = cidadaoRepository.getList();
    assertThat(cidadaos).isNotNull();
    assertThat(cidadaos.size()).isEqualTo(3);
  }

  @Test
  public void getCidadaosAltTest() {
    cidadaoRepository.deleteAll();

    Cidadao c1 = new Cidadao();
    cidadaoRepository.save(c1);
    Cidadao c2 = new Cidadao();
    cidadaoRepository.save(c2);
    Cidadao c3 = new Cidadao();
    cidadaoRepository.save(c3);
    List<Cidadao> cidadaos = cidadaoRepository.getList();
    assertThat(cidadaos).isNotNull();
    assertThat(cidadaos.size()).isEqualTo(3);
  }

  @Test
  public void getDelegadosTest() {
    cidadaoRepository.deleteAll();

    Cidadao c = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c);
    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);
    Delegado d2 = new Delegado("ll", "luis", "nnnnn");
    cidadaoRepository.save(d2);
    List<Delegado> delegados = cidadaoRepository.getListDelegado();
    assertThat(delegados).isNotNull();
    assertThat(delegados.size()).isEqualTo(2);
  }

  @Test
  public void getCidadaosNaoDelegadosTest() {
    cidadaoRepository.deleteAll();
    Cidadao c = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c);
    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);
    Delegado d2 = new Delegado("ll", "luis", "nnnnn");
    cidadaoRepository.save(d2);
    List<Cidadao> cidadaos = cidadaoRepository.getListCidadao();
    assertThat(cidadaos).isNotNull();
    assertThat(cidadaos.size()).isEqualTo(1);
  }

  @Test
  public void getCidadaosNaoDelegadosAltTest() {
    cidadaoRepository.deleteAll();
    Cidadao c = new Cidadao();
    cidadaoRepository.save(c);
    Delegado d1 = new Delegado();
    cidadaoRepository.save(d1);
    Delegado d2 = new Delegado();
    cidadaoRepository.save(d2);
    List<Cidadao> cidadaos = cidadaoRepository.getListCidadao();
    assertThat(cidadaos).isNotNull();
    assertThat(cidadaos.size()).isEqualTo(1);
  }

  @Test
  public void getCidadaoByIdComParamsTest() {
    cidadaoRepository.deleteAll();
    Cidadao c = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c);
    Cidadao cidadaoPorId = cidadaoRepository.findById(c.getId()).get();
    assertThat(cidadaoPorId).isNotNull();
  }

  @Test
  public void getCidadaoByCcComParamsTest() {
    cidadaoRepository.deleteAll();
    Cidadao c = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c);
    Cidadao cidadaoPorCC = cidadaoRepository.findByCC(c.getNumero_de_cc());
    assertThat(cidadaoPorCC).isNotNull();
  }

  // CASO DE USO J
  @Test
  public void SemPropostasTest() throws Exception {
    propostaRepository.deleteAll();
    cidadaoRepository.deleteAll();

    VotarPropostaHandler vph =
        new VotarPropostaHandler(propostaRepository, delegadoPropostaRepository);
    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);
    Proposta propostaEscolhida = vph.votarNumaProposta(c1);
    assertThat(propostaEscolhida).isNull();
  }

  @Test
  public void naoVotaPropostaTest() throws Exception {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();
    delegadoPropostaRepository.deleteAll();
    propostaRepository.deleteAll();
    delegacaoDeVotoRepository.deleteAll();

    VotarPropostaHandler vph =
        new VotarPropostaHandler(propostaRepository, delegadoPropostaRepository);
    ApoiarProjetoHandler ap = new ApoiarProjetoHandler(projetoDeLeiRepository, propostaRepository);
    EscolherDelegadoHandler edh =
        new EscolherDelegadoHandler(cidadaoRepository, delegacaoDeVotoRepository);

    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);

    Cidadao c2 = new Cidadao("123456", "pedro", "9999");
    cidadaoRepository.save(c2);

    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Geral");
    temaRepository.save(t1);

    Tema t2 = new Tema("Saude");
    t2.setParent(t1);
    temaRepository.save(t2);
    ProjetoDeLei p1 =
        new ProjetoDeLei(
            "projeto1", new File("testepdf.txt"), new Date(), new Date(), "descricao1", d1, t1);
    p1.setApoio(10000);
    projetoDeLeiRepository.save(p1);
    edh.escolherDelegado(c1, t1, d1);
    Long projetoid = ap.apoiarprojeto(p1, c1);
    String input = projetoid + "\nN\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));

    Proposta propostaEscolhida = vph.votarNumaProposta(c1);
    Delegado dd = c1.getDelegadoDoTema(propostaEscolhida.getTema());
    DelegadoProposta a =
        delegadoPropostaRepository.getDecisaoByDelegadoProposta(propostaEscolhida, dd);

    assertThat(a.getFlag()).isEqualTo(true);
    int[] todosVotos = propostaEscolhida.getVotosComContra();
    int sum = 0;
    for (int i = 0; i < 2; i++) {
      sum += todosVotos[i];
    }
    assertThat(sum).isEqualTo(1);
  }

  @Test
  public void votaPropostaTest() throws Exception {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();
    delegadoPropostaRepository.deleteAll();
    propostaRepository.deleteAll();
    delegacaoDeVotoRepository.deleteAll();

    VotarPropostaHandler vph =
        new VotarPropostaHandler(propostaRepository, delegadoPropostaRepository);
    ApoiarProjetoHandler ap = new ApoiarProjetoHandler(projetoDeLeiRepository, propostaRepository);
    EscolherDelegadoHandler edh =
        new EscolherDelegadoHandler(cidadaoRepository, delegacaoDeVotoRepository);

    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);

    Cidadao c2 = new Cidadao("123456", "pedro", "9999");
    cidadaoRepository.save(c2);

    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Geral");
    temaRepository.save(t1);

    Tema t2 = new Tema("Saude");
    t2.setParent(t1);
    temaRepository.save(t2);
    ProjetoDeLei p1 =
        new ProjetoDeLei(
            "projeto1", new File("testepdf.txt"), new Date(), new Date(), "descricao1", d1, t1);
    p1.setApoio(10000);
    projetoDeLeiRepository.save(p1);

    edh.escolherDelegado(c1, t1, d1);
    Long projetoid = ap.apoiarprojeto(p1, c1);
    String input = projetoid + "\nS\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));

    Proposta propostaEscolhida = vph.votarNumaProposta(c1);

    List<Cidadao> cidadaos = propostaEscolhida.getListaDePessoasVotos();
    assertThat(cidadaos.size()).isEqualTo(1);
    assertThat(cidadaos.get(0).getNome()).isEqualTo(c1.getNome());
    int[] todosVotos = propostaEscolhida.getVotosComContra();
    int sum = 0;
    for (int i = 0; i < 2; i++) {
      sum += todosVotos[i];
    }
    assertThat(sum).isEqualTo(2);
  }

  @Test
  public void naoTemDelegadoENaoQuerVotarTest() throws Exception {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();
    delegadoPropostaRepository.deleteAll();
    propostaRepository.deleteAll();
    delegacaoDeVotoRepository.deleteAll();

    VotarPropostaHandler vph =
        new VotarPropostaHandler(propostaRepository, delegadoPropostaRepository);
    ApoiarProjetoHandler ap = new ApoiarProjetoHandler(projetoDeLeiRepository, propostaRepository);

    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);

    Cidadao c2 = new Cidadao("123456", "pedro", "9999");
    cidadaoRepository.save(c2);

    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Geral");
    temaRepository.save(t1);

    Tema t2 = new Tema("Saude");
    t2.setParent(t1);
    temaRepository.save(t2);
    ProjetoDeLei p1 =
        new ProjetoDeLei(
            "projeto1", new File("testepdf.txt"), new Date(), new Date(), "descricao1", d1, t1);
    p1.setApoio(10000);
    projetoDeLeiRepository.save(p1);

    Long projetoid = ap.apoiarprojeto(p1, c1);
    String input = projetoid + "\nN\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));

    Proposta propostaEscolhida = vph.votarNumaProposta(c1);

    List<Cidadao> cidadaos = propostaEscolhida.getListaDePessoasVotos();
    assertThat(cidadaos.size()).isEqualTo(0);
    // assertThat(cidadaos.get(0).getNome()).isEqualTo(c1.getNome());
    int[] todosVotos = propostaEscolhida.getVotosComContra();
    int sum = 0;
    for (int i = 0; i < 2; i++) {
      sum += todosVotos[i];
    }
    assertThat(sum).isEqualTo(1);
  }

  @Test
  public void naoTemDelegadoEQuerVotarTest() throws Exception {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();
    delegadoPropostaRepository.deleteAll();
    propostaRepository.deleteAll();
    delegacaoDeVotoRepository.deleteAll();

    VotarPropostaHandler vph =
        new VotarPropostaHandler(propostaRepository, delegadoPropostaRepository);
    ApoiarProjetoHandler ap = new ApoiarProjetoHandler(projetoDeLeiRepository, propostaRepository);

    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);

    Cidadao c2 = new Cidadao("123456", "pedro", "9999");
    cidadaoRepository.save(c2);

    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);
    Tema t1 = new Tema("Geral");
    temaRepository.save(t1);

    Tema t2 = new Tema("Saude");
    t2.setParent(t1);
    temaRepository.save(t2);
    ProjetoDeLei p1 =
        new ProjetoDeLei(
            "projeto1", new File("testepdf.txt"), new Date(), new Date(), "descricao1", d1, t1);
    p1.setApoio(10000);
    projetoDeLeiRepository.save(p1);

    Long projetoid = ap.apoiarprojeto(p1, c1);
    String input = projetoid + "\nS\nS\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));

    Proposta propostaEscolhida = vph.votarNumaProposta(c1);

    List<Cidadao> cidadaos = propostaEscolhida.getListaDePessoasVotos();
    assertThat(cidadaos.size()).isEqualTo(1);
    assertThat(cidadaos.get(0).getNome()).isEqualTo(c1.getNome());
    int[] todosVotos = propostaEscolhida.getVotosComContra();
    int sum = 0;
    for (int i = 0; i < 2; i++) {
      sum += todosVotos[i];
    }
    assertThat(sum).isEqualTo(2);
  }

  @Test
  public void encontrarDelegadoMaisPertoTest() throws Exception {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();
    delegadoPropostaRepository.deleteAll();
    propostaRepository.deleteAll();
    delegacaoDeVotoRepository.deleteAll();

    VotarPropostaHandler vph =
        new VotarPropostaHandler(propostaRepository, delegadoPropostaRepository);
    ApoiarProjetoHandler ap = new ApoiarProjetoHandler(projetoDeLeiRepository, propostaRepository);
    EscolherDelegadoHandler edh =
        new EscolherDelegadoHandler(cidadaoRepository, delegacaoDeVotoRepository);

    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);

    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);

    Delegado d2 = new Delegado("123456", "pedro", "9999");
    cidadaoRepository.save(d2);

    Tema t1 = new Tema("Geral");
    temaRepository.save(t1);

    Tema t2 = new Tema("Saude");
    t2.setParent(t1);
    temaRepository.save(t2);

    Tema t3 = new Tema("Vacinas");
    t3.setParent(t2);
    temaRepository.save(t3);

    edh.escolherDelegado(c1, t1, d2);

    Delegado result = c1.getDelegadoDoTema(t3);

    assertThat(result.getNome()).isEqualTo("pedro");
  }

  @Test
  public void naoTemDelegadoMaisPertoTest() throws Exception {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();
    delegadoPropostaRepository.deleteAll();
    propostaRepository.deleteAll();
    delegacaoDeVotoRepository.deleteAll();

    VotarPropostaHandler vph =
        new VotarPropostaHandler(propostaRepository, delegadoPropostaRepository);
    ApoiarProjetoHandler ap = new ApoiarProjetoHandler(projetoDeLeiRepository, propostaRepository);
    EscolherDelegadoHandler edh =
        new EscolherDelegadoHandler(cidadaoRepository, delegacaoDeVotoRepository);

    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);

    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);

    Delegado d2 = new Delegado("123456", "pedro", "9999");
    cidadaoRepository.save(d2);

    Tema t1 = new Tema("Geral");
    temaRepository.save(t1);

    Tema t2 = new Tema("Saude");
    t2.setParent(t1);
    temaRepository.save(t2);

    Tema t3 = new Tema("Vacinas");
    t3.setParent(t2);
    temaRepository.save(t3);

    Delegado result = c1.getDelegadoDoTema(t3);

    assertThat(result).isNull();
  }

  // CASO DE USO K
  @Test
  public void atribuirVotosTest() throws Exception {
    cidadaoRepository.deleteAll();
    temaRepository.deleteAll();
    projetoDeLeiRepository.deleteAll();
    delegadoPropostaRepository.deleteAll();
    propostaRepository.deleteAll();
    delegacaoDeVotoRepository.deleteAll();

    VotarPropostaHandler vph =
        new VotarPropostaHandler(propostaRepository, delegadoPropostaRepository);
    ApoiarProjetoHandler ap = new ApoiarProjetoHandler(projetoDeLeiRepository, propostaRepository);
    EscolherDelegadoHandler edh =
        new EscolherDelegadoHandler(cidadaoRepository, delegacaoDeVotoRepository);
    FecharVotacaoHandler fvh = new FecharVotacaoHandler(cidadaoRepository);

    Tema t1 = new Tema("Geral");
    temaRepository.save(t1);

    Tema t2 = new Tema("Saude");
    t2.setParent(t1);
    temaRepository.save(t2);

    Tema t3 = new Tema("Vacinas");
    t3.setParent(t2);
    temaRepository.save(t3);

    Cidadao c1 = new Cidadao("123456789", "bruno", "dnsa");
    cidadaoRepository.save(c1);

    Cidadao c2 = new Cidadao("123456", "pedro", "9999");
    cidadaoRepository.save(c2);

    Cidadao c3 = new Cidadao("123456", "andre", "9999");
    cidadaoRepository.save(c3);

    Cidadao c4 = new Cidadao("123456", "joaquim", "9999");
    cidadaoRepository.save(c4);

    Delegado d1 = new Delegado("kk", "joao", "bbbbb");
    cidadaoRepository.save(d1);

    Delegado d2 = new Delegado("kk", "tiago", "bbbbb");
    cidadaoRepository.save(d2);

    Delegado d3 = new Delegado("kk", "ruben", "bbbbb");
    cidadaoRepository.save(d3);

    Delegado d4 = new Delegado("kk", "jonas", "bbbbb");
    cidadaoRepository.save(d4);

    ProjetoDeLei p1 =
        new ProjetoDeLei(
            "projeto1", new File("testepdf.txt"), new Date(), new Date(), "descricao1", d1, t3);
    p1.setApoio(10000);
    projetoDeLeiRepository.save(p1);

    Long projetoid = ap.apoiarprojeto(p1, d2);

    String input = projetoid + "\nS\nS"; // Nao tem delegado, e vota a favor
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Proposta propostaEscolhida = vph.votarNumaProposta(d2);

    input = projetoid + "\nS\nN"; // Nao tem delegado, e vota contra
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    propostaEscolhida = vph.votarNumaProposta(d3);

    input = projetoid + "\nS\nS"; // Nao tem delegado, e vota contra
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    propostaEscolhida = vph.votarNumaProposta(d4);

    edh.escolherDelegado(c1, t1, d1);
    input = projetoid + "\nS"; // Nao concorda com o voto do seu delegado
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    propostaEscolhida = vph.votarNumaProposta(c1);

    edh.escolherDelegado(c2, t2, d2);
    input = projetoid + "\nN"; // Concorda com o voto do seu delegado
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    propostaEscolhida = vph.votarNumaProposta(c2);

    edh.escolherDelegado(c3, t3, d3);
    input = projetoid + "\nN"; // Concorda com o voto do seu delegado
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    propostaEscolhida = vph.votarNumaProposta(c3);

    input = projetoid + "\nN"; // Nao tem delegado e nao vota
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    propostaEscolhida = vph.votarNumaProposta(c4);

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.DATE, 60);
    Date dataForaDoPrazo = calendar.getTime();

    fvh.fecharVotacao(dataForaDoPrazo, propostaEscolhida);

    assertThat(propostaEscolhida.getAprovacao()).isEqualTo(Aprovacao.APROVADA);
    assertThat((propostaEscolhida.getVotosComContra())[0]).isEqualTo(4);
    assertThat((propostaEscolhida.getVotosComContra())[1]).isEqualTo(3);
  }
}
