package pt.ul.fc.css.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
  @GetMapping({"/", "/menu"})
  public String menuPrincipalPagina() {
    return "menu";
  }

  @GetMapping("/escolherDelegado")
  public String escolherDelegadoPagina() {
    return "escolherDelegado";
  }

  @GetMapping("/apresentarProjetoLei")
  public String apresentarProjetoLeiPagina() {
    return "apresentarProjetoLei";
  }
}
