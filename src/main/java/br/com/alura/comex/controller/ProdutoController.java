package br.com.alura.comex.controller;


import br.com.alura.comex.model.ProdutoResponse;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

  private final ProdutoRepository repository;

  public ProdutoController(@Lazy ProdutoRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public Page<ProdutoResponse> findAll(
      @PageableDefault(size = 5, sort = "nome") Pageable pageable) {
    return this.repository.findAll(pageable).map(ProdutoResponse::new);
  }

}
