package br.com.alura.comex.service;

import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public Optional<Usuario> findByEmail(String email) {
    return this.usuarioRepository.findByEmail(email);
  }
}
