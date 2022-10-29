package br.com.alura.comex.service;

import br.com.alura.comex.controller.domain.ClienteProjectionResponse;
import br.com.alura.comex.controller.domain.ClienteRequest;
import br.com.alura.comex.model.Cliente;
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

  public Long add(ClienteRequest request) {
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
    return this.clienteRepository
        .save(cliente)
        .getId();
  }

  public Page<ClienteProjectionResponse> findAllProjection(Pageable pageable) {
    return this.clienteRepository.findAllProjection(pageable).map(ClienteProjectionResponse::new);
  }

  public Optional<Cliente> findById(Long id) {
    return this.clienteRepository.findById(id);
  }
}
