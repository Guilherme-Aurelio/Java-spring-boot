package SC.SC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


import SC.SC.domain.usuario.DadosAutenticacao;
import SC.SC.domain.usuario.Usuario;

import SC.SC.domain.usuario.DadosAutenticacao;
import SC.SC.domain.usuario.Usuario;
import SC.SC.infra.security.DadosTokenJWT;
import SC.SC.service.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
  
  @Autowired
  private AuthenticationManager authenticationManager;

  
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  

  @Autowired
  TokenService tokenService;

  @PostMapping
  public ResponseEntity<Object> efetuarLogin(@RequestBody DadosAutenticacao dados){
  try {
      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(dados.login(), dados.senha())
      );

      Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
      String tokenJWT = tokenService.gerarToken(usuarioAutenticado);

      return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    } catch (DisabledException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Conta de usuário desativada");
    }
  }

   
  @GetMapping
  public ResponseEntity<String> getSenhaBcrypt(@RequestBody String senha){
    String senhaBrypt = bCryptPasswordEncoder.encode(senha);
    return ResponseEntity.ok(senhaBrypt);
  }
}