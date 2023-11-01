package SC.SC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;


import SC.SC.domain.tarefa.Tarefa;
import SC.SC.domain.usuario.Usuario;
import SC.SC.repository.TarefaRepository;
import SC.SC.repository.UsuarioRepository;
import jakarta.validation.Valid;
import SC.SC.service.TokenService;

@RestController
@RequestMapping("usuarios")

public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    TokenService tokenService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder){
        String senhaCriptografada = bCryptPasswordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        Usuario usuarioLocal = repository.save(usuario);
        String token = tokenService.gerarToken(usuario);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioLocal.getId()).toUri();
        return ResponseEntity.created(uri).headers(headers).build();
    }
}
