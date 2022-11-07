package br.com.alura.comex.controller;

import br.com.alura.comex.controller.domain.PedidoDetailResponse;
import br.com.alura.comex.controller.domain.PedidoRequest;
import br.com.alura.comex.exception.UnauthorizedException;
import br.com.alura.comex.service.PedidoService;
import br.com.alura.comex.service.TokenService;
import br.com.alura.comex.service.UsuarioService;
import javax.validation.Valid;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

  private final PedidoService pedidoService;
  private final TokenService tokenService;
  private final UsuarioService usuarioService;

  public PedidoController(
      @Lazy PedidoService pedidoService,
      @Lazy TokenService tokenService,
      @Lazy UsuarioService usuarioService) {
    this.pedidoService = pedidoService;
    this.tokenService = tokenService;
    this.usuarioService = usuarioService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void add(@Valid @RequestBody PedidoRequest request) {
    this.pedidoService.add(request);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PedidoDetailResponse> findById(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
      @Valid @PathVariable("id") Long id) {
    var token = authorization.replace("Bearer ", "");
    var usuario = this.usuarioService
        .findByEmail(this.tokenService.getSubject(token))
        .orElseThrow();
    var pedido = this.pedidoService
        .findById(id);

    if (!pedido.orElseThrow().getCliente().equals(usuario.getCliente())) {
      throw new UnauthorizedException();
    }

    return ResponseEntity.of(pedido.map(PedidoDetailResponse::new));
  }
}
