package br.com.alura.comex.service;

import br.com.alura.comex.controller.domain.PedidoRequest;
import br.com.alura.comex.controller.domain.ProdutoRequest;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.ProdutoProjection;
import br.com.alura.comex.repository.ProdutoRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

  private final ProdutoRepository produtoRepository;
  private final CategoriaService categoriaService;

  public ProdutoService(
      @Lazy ProdutoRepository produtoRepository,
      @Lazy CategoriaService categoriaService) {
    this.produtoRepository = produtoRepository;
    this.categoriaService = categoriaService;
  }

  public Page<ProdutoProjection> findAllProjection(Pageable pageable) {
    return this.produtoRepository.findAllProjection(pageable);
  }

  public Optional<Produto> findById(Long id) {
    return this.produtoRepository.findById(id);
  }

  public boolean existsById(Long id) {
    return this.produtoRepository.existsById(id);
  }

  @Transactional
  public Long add(ProdutoRequest request) {
    var produto = save(request);
    return produto.getId();
  }

  @Transactional
  public Produto save(Produto produto) {
    return this.produtoRepository.saveAndFlush(produto);
  }

  @Transactional
  public void updateStock(PedidoRequest request) {
    request.getProdutos().forEach(itemRequest -> {
      var product = this.produtoRepository.findById(itemRequest.getIdProduto()).orElseThrow();
      product.setQuantidadeEstoque(product.getQuantidadeEstoque() - itemRequest.getQuantidade());
    });
  }

  @Transactional
  public Produto save(ProdutoRequest request) {
    var categoria = this.categoriaService.findById(request.getIdCategoria()).orElseThrow();
    var produto = new Produto();
    produto.setCategoria(categoria);
    produto.setNome(request.getNome());
    produto.setDescricao(request.getDescricao());
    produto.setPrecoUnitario(request.getPrecoUnitario());
    produto.setQuantidadeEstoque(request.getQuantidadeEstoque());
    this.produtoRepository.saveAndFlush(produto);
    categoria.adicionarProduto(produto);
    this.categoriaService.save(categoria);
    return produto;
  }

}
