package br.com.alura.comex.model;

import java.math.BigDecimal;

public class ProdutoResponse {

  private String nome;

  private BigDecimal preco;

  private String descricao;

  private int quantidadeEstoque;

  private Long idCategoria;

  private String nomeCategoria;

  public ProdutoResponse(Produto produto) {
    this.nome = produto.getNome();
    this.preco = produto.getPrecoUnitario();
    this.descricao = produto.getDescricao();
    this.quantidadeEstoque = produto.getQuantidadeEstoque();
    this.idCategoria = produto.getCategoria().getId();
    this.nomeCategoria = produto.getCategoria().getNome();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public int getQuantidadeEstoque() {
    return quantidadeEstoque;
  }

  public void setQuantidadeEstoque(int quantidadeEstoque) {
    this.quantidadeEstoque = quantidadeEstoque;
  }

  public Long getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(Long idCategoria) {
    this.idCategoria = idCategoria;
  }

  public String getNomeCategoria() {
    return nomeCategoria;
  }

  public void setNomeCategoria(String nomeCategoria) {
    this.nomeCategoria = nomeCategoria;
  }
}
