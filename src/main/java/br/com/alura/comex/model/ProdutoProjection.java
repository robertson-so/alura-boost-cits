package br.com.alura.comex.model;

import java.math.BigDecimal;

public interface ProdutoProjection {

  Long getIdProduto();

  String getNome();

  BigDecimal getPreco();

  String getDescricao();

  Integer getQuantidadeEstoque();

  Long getIdCategoria();

  String getNomeCategoria();

}
