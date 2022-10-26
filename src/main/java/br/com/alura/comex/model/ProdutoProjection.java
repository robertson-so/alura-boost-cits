package br.com.alura.comex.model;

import java.math.BigDecimal;

public interface ProdutoProjection {

  String getNome();

  BigDecimal getPreco();

  String getDescricao();

  int getQuantidadeEstoque();

  Long getIdCategoria();

  String getNomeCategoria();

}
