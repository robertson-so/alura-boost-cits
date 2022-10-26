package br.com.alura.comex.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItemDePedidoRequest {

  @NotNull
  private Long idProduto;

  @Positive
  private Integer quantidade;

  public ItemDePedidoRequest() {
    //
  }

  public Long getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(Long idProduto) {
    this.idProduto = idProduto;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }
}
