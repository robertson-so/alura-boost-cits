package br.com.alura.comex.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoriaRequest {

  @NotEmpty
  @Size(min = 2)
  private String nome;

  public CategoriaRequest() {
    //
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
