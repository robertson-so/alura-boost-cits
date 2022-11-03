package br.com.alura.comex.service;

import br.com.alura.comex.controller.domain.CategoriaRequest;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;
import java.util.Optional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  public Categoria save(Categoria categoria) {
    return this.categoriaRepository
        .save(categoria);
  }

  public Optional<Categoria> findById(Long id) {
    return this.categoriaRepository
        .findById(id);
  }

  public Boolean existsById(Long id) {
    return this.categoriaRepository
        .existsById(id);
  }

  public Page<Categoria> findAll(Pageable pageable) {
    return this.categoriaRepository
        .findAll(pageable);
  }
}
