package SC.SC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
/*import org.springframework.security.core.token.TokenService;*/
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(login) 

public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //@Autowired 
    //TokenService tokenService;

    //@PostMapping
    
}
