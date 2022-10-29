package br.com.alura.comex.controller;


import br.com.alura.comex.controller.domain.ProdutoProjectionResponse;
import br.com.alura.comex.controller.domain.ProdutoRequest;
import br.com.alura.comex.service.ProdutoService;
import javax.validation.Valid;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

  private final ProdutoService produtoService;

  public ProdutoController(@Lazy ProdutoService produtoService) {
    this.produtoService = produtoService;
  }

  @PostMapping
  public ResponseEntity<Void> add(@Valid @RequestBody ProdutoRequest request) {
    var id = this.produtoService
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

  @GetMapping
  public Page<ProdutoProjectionResponse> findAll(
      @PageableDefault(size = 5, sort = "nome") Pageable pageable) {
    return this.produtoService
        .findAllProjection(pageable)
        .map(ProdutoProjectionResponse::new);
  }
}
