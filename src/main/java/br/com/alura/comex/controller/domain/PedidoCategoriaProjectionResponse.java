package br.com.alura.comex.controller.domain;

import br.com.alura.comex.model.PedidoCategoriaProjection;
import java.math.BigDecimal;

public final class PedidoCategoriaProjectionResponse {

  private final String categoria;
  private final Integer quantidadeProdutosVendidos;
  private final BigDecimal montanteVendido;

  public PedidoCategoriaProjectionResponse(PedidoCategoriaProjection pedidoCategoriaProjection) {
    this.categoria = pedidoCategoriaProjection.getCategoria();
    this.quantidadeProdutosVendidos = pedidoCategoriaProjection.getQuantidadeProdutosVendidos();
    this.montanteVendido = pedidoCategoriaProjection.getMontanteVendido();
  }

  public String getCategoria() {
    return categoria;
  }

  public Integer getQuantidadeProdutosVendidos() {
    return quantidadeProdutosVendidos;
  }

  public BigDecimal getMontanteVendido() {
    return montanteVendido;
  }
}
