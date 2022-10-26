package br.com.alura.comex.controller;

import br.com.alura.comex.model.PedidoRequest;
import br.com.alura.comex.repository.PedidoRepository;
import javax.validation.Valid;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

  private final PedidoRepository pedidoRepository;

  public PedidoController(@Lazy PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void add(@RequestBody @Valid PedidoRequest request) {

  }
}
