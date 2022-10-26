package br.com.alura.comex.service;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;

  public CategoriaService(@Lazy CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public void add(Categoria request) {
    request.setStatus(StatusCategoria.ATIVA);
    this.categoriaRepository.save(request);
  }
}
