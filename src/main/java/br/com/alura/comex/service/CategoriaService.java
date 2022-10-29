package br.com.alura.comex.service;

import br.com.alura.comex.controller.domain.CategoriaRequest;
import br.com.alura.comex.controller.domain.CategoriaResponse;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;
import java.util.Optional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;

  public CategoriaService(@Lazy CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public Long add(CategoriaRequest request) {
    var categoria = new Categoria();
    categoria.setNome(request.getNome());
    categoria.setStatus(StatusCategoria.ATIVA);
    return this.categoriaRepository
        .save(categoria)
        .getId();
  }

  public Optional<CategoriaResponse> findById(Long id) {
    return this.categoriaRepository
        .findById(id)
        .map(CategoriaResponse::new);
  }
}
