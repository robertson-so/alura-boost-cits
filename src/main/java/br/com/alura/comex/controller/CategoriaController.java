package br.com.alura.comex.controller;

import br.com.alura.comex.controller.domain.CategoriaListaResponse;
import br.com.alura.comex.controller.domain.CategoriaRequest;
import br.com.alura.comex.controller.domain.CategoriaResponse;
import br.com.alura.comex.controller.domain.PedidoCategoriaProjectionResponse;
import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.annotation.security.DeclareRoles;
import javax.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/categorias")
@DeclareRoles("FUBAR")
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
  @Operation(summary = "Adicionar categoria", security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<Void> add(@Valid @RequestBody CategoriaRequest request) {
    var id = this.categoriaService
        .add(request);
    var location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(id)
        .toUri();
    return ResponseEntity
        .created(location)
        .build();
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<CategoriaResponse> findById(@Valid @PathVariable Long id) {
    return ResponseEntity
        .of(this.categoriaService
            .findById(id)
            .map(CategoriaResponse::new));
  }

  @GetMapping(value = "/pedidos")
  @Cacheable(value = "listaDePedidos")
  public Page<PedidoCategoriaProjectionResponse> findPedidos(Pageable pageable) {
    return this.pedidoService
        .findVendidos(pageable);
  }

  @GetMapping
  public Page<CategoriaListaResponse> findAll(Pageable pageable) {
    return this.categoriaService.findAll(pageable)
        .map(CategoriaListaResponse::new);
  }

}
