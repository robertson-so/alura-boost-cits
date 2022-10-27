package br.com.alura.comex.service;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.ClienteProjection;
import br.com.alura.comex.model.ClienteRequest;
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

  public void add(ClienteRequest request) {
    var cliente = new Cliente();
    cliente.setNome(request.getNome());
    cliente.setBairro(request.getBairro());
    cliente.setCidade(request.getCidade());
    cliente.setCpf(request.getCpf());
    cliente.setEstado(request.getEstado());
    cliente.setComplemento(request.getComplemento());
    cliente.setNumero(request.getNumero());
    cliente.setRua(request.getRua());
    cliente.setTelefone(request.getTelefone());
    this.clienteRepository.save(cliente);
  }

  public Page<ClienteProjection> findAllProjection(Pageable pageable) {
    return this.clienteRepository.findAllProjection(pageable);
  }

  public Optional<Cliente> findById(Long id) {
    return this.clienteRepository.findById(id);
  }
}
