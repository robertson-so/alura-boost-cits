package br.com.alura.comex.service;

import br.com.alura.comex.model.ProdutoProjection;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

  private final ProdutoRepository produtoRepository;

  public ProdutoService(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  public Page<ProdutoProjection> findAllProjection(Pageable pageable) {
    return this.produtoRepository.findAllProjection(pageable);
  }
}
