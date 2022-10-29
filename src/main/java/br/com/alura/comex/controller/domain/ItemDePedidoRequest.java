package br.com.alura.comex.controller.domain;

import br.com.alura.comex.validator.ExistsProduto;
import br.com.alura.comex.validator.HasStock;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@HasStock
public final class ItemDePedidoRequest {

  @NotNull
  @ExistsProduto
  private Long idProduto;

  @NotNull
  @Positive
  private Integer quantidade;

  public ItemDePedidoRequest() {
    //
  }

  public Long getIdProduto() {
    return idProduto;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

}
