package br.com.alura.comex.controller.domain;

import br.com.alura.comex.model.Pedido;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class PedidoDetailResponse {

  private final LocalDate dataPedido;

  private final BigDecimal valor;

  private final BigDecimal desconto;

  private final List<ItemDePedidoDetailResponse> itens;

  private final ClienteResponse cliente;

  public PedidoDetailResponse(Pedido pedido) {
    this.dataPedido = pedido.getData();
    this.itens = new ArrayList<>();
    pedido.getItens().forEach(item -> itens.add(new ItemDePedidoDetailResponse(item)));
    this.valor = itens.stream()
        .map(ItemDePedidoDetailResponse::getValorPago)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    this.desconto = itens.stream()
        .map(ItemDePedidoDetailResponse::getDesconto)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    this.cliente = new ClienteResponse(pedido.getCliente().getId(), pedido.getCliente().getNome());
  }

  public LocalDate getDataPedido() {
    return dataPedido;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }

  public List<ItemDePedidoDetailResponse> getItens() {
    return itens;
  }

  public ClienteResponse getCliente() {
    return cliente;
  }
}
