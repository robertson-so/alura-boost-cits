package br.com.alura.comex.controller.domain;

import br.com.alura.comex.model.ItemDePedido;
import java.math.BigDecimal;

public final class ItemDePedidoDetailResponse {

  private final Long idItem;

  private final Long idProduto;

  private final String nomeProduto;

  private final String nomeCategoria;

  private final Integer quantidade;

  private final BigDecimal precoUnitario;

  private final BigDecimal valorPago;

  private final BigDecimal desconto;

  public ItemDePedidoDetailResponse(ItemDePedido itemDePedido) {
    this.idItem = itemDePedido.getId();
    this.idProduto = itemDePedido.getProduto().getId();
    this.nomeProduto = itemDePedido.getProduto().getNome();
    this.nomeCategoria = itemDePedido.getProduto().getCategoria().getNome();
    this.quantidade = itemDePedido.getQuantidade();
    this.precoUnitario = itemDePedido.getPrecoUnitario();
    this.valorPago = itemDePedido.getValorTotalItem()
        .subtract(itemDePedido.getValorTotalItem()
            .multiply(itemDePedido.getDesconto()));
    this.desconto = itemDePedido.getValorTotalItem()
        .multiply(itemDePedido.getDesconto());
  }

  public Long getIdItem() {
    return idItem;
  }

  public Long getIdProduto() {
    return idProduto;
  }

  public String getNomeProduto() {
    return nomeProduto;
  }

  public String getNomeCategoria() {
    return nomeCategoria;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public BigDecimal getValorPago() {
    return valorPago;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }
}
