package br.com.alura.comex.controller.domain;

import br.com.alura.comex.model.Categoria;

public final class CategoriaResponse {

  private final Long id;

  private final String nome;

  public CategoriaResponse(Categoria categoria) {
    this.id = categoria.getId();
    this.nome = categoria.getNome();
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }
}
