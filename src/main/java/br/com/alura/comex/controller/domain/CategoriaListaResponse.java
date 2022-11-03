package br.com.alura.comex.controller.domain;

import br.com.alura.comex.model.Categoria;

public final class CategoriaListaResponse {

  private final Long id;

  private final String nome;

  public CategoriaListaResponse(Categoria categoria) {
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
