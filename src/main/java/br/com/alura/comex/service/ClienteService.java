package br.com.alura.comex.service;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.ClienteProjection;
import br.com.alura.comex.repository.ClienteRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;

  public ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  public void add(Cliente request) {
    this.clienteRepository.save(request);
  }

  public Page<ClienteProjection> findAllProjection(Pageable pageable) {
    return this.clienteRepository.findAllProjection(pageable);
  }

  public Optional<Cliente> findById(Long id) {
    return this.clienteRepository.findById(id);
  }
}
