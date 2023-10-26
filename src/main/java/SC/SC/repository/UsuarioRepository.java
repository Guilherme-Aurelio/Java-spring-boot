package SC.SC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import SC.SC.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

  UserDetails findByLogin(String username);
  
}