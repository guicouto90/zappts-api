package test.zapptsmagicapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IntroductionController {
  @GetMapping
  public @ResponseBody String hello() {
    return "Olá, essa é uma API para o teste técnico da empresa Zappts, consulte a documentação: https://github.com/guicouto90/zappts-api, para acessar as rotas corretas.\n\n Qualquer duvida ou sugestão me contate por: \n Linkedin: https://www.linkedin.com/in/guicouto90/ \n Email: gui.couto90@yahoo.com.br";
  }
}
