package br.com.alura.comex.controller.domain;

import br.com.alura.comex.model.ProdutoProjection;
import java.math.BigDecimal;

public final class ProdutoProjectionResponse {

  private final Long idProduto;
  private final String nome;
  private final BigDecimal preco;
  private final String descricao;
  private final int quantidadeEstoque;
  private final Long idCategoria;
  private final String nomeCategoria;

  public ProdutoProjectionResponse(ProdutoProjection produtoProjection) {
    this.idProduto = produtoProjection.getIdProduto();
    this.nome = produtoProjection.getNome();
    this.preco = produtoProjection.getPreco();
    this.descricao = produtoProjection.getDescricao();
    this.quantidadeEstoque = produtoProjection.getQuantidadeEstoque();
    this.idCategoria = produtoProjection.getIdCategoria();
    this.nomeCategoria = produtoProjection.getNomeCategoria();
  }

  public Long getIdProduto() {
    return idProduto;
  }

  public String getNome() {
    return nome;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public String getDescricao() {
    return descricao;
  }

  public int getQuantidadeEstoque() {
    return quantidadeEstoque;
  }

  public Long getIdCategoria() {
    return idCategoria;
  }

  public String getNomeCategoria() {
    return nomeCategoria;
  }
}
