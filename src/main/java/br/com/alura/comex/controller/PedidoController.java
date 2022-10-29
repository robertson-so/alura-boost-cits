package br.com.alura.comex.controller;

import br.com.alura.comex.controller.domain.PedidoRequest;
import br.com.alura.comex.service.PedidoService;
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

  private final PedidoService pedidoService;

  public PedidoController(@Lazy PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void add(@RequestBody @Valid PedidoRequest request) {
    this.pedidoService.add(request);
  }
}
