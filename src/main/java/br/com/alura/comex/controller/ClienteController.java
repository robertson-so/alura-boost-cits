package br.com.alura.comex.controller;


import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.ClienteProjection;
import br.com.alura.comex.repository.ClienteRepository;
import javax.validation.Valid;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

  private final ClienteRepository clienteRepository;

  public ClienteController(@Lazy ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void add(@Valid @RequestBody Cliente request) {
    this.clienteRepository.save(request);
  }

  @GetMapping
  public Page<ClienteProjection> findAll(@PageableDefault(size = 5, sort = "nome") Pageable pageable) {
    return this.clienteRepository.findAllProjecao(pageable);
  }

}
