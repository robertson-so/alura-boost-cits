package br.com.alura.comex.controller;

import br.com.alura.comex.model.CategoriaRequest;
import br.com.alura.comex.model.PedidoCategoriaProjection;
import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.PedidoService;
import javax.validation.Valid;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaController {

  private final CategoriaService categoriaService;
  private final PedidoService pedidoService;

  public CategoriaController(
      @Lazy CategoriaService categoriaService,
      @Lazy PedidoService pedidoService) {
    this.categoriaService = categoriaService;
    this.pedidoService = pedidoService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void add(@Valid @RequestBody CategoriaRequest request) {
    this.categoriaService.add(request);
  }

  @GetMapping(value = "/pedidos")
  public Page<PedidoCategoriaProjection> findPedidos(Pageable pageable) {
    return this.pedidoService.findVendidos(pageable);
  }

}
