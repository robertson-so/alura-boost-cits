package br.com.alura.comex.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PedidoRequest {

  @NotNull
  private Long idCliente;

  @NotEmpty
  private List<@Valid ItemDePedidoRequest> produtos = new ArrayList<>();

  public PedidoRequest() {
    //
  }

  public Long getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Long idCliente) {
    this.idCliente = idCliente;
  }

  public List<ItemDePedidoRequest> getProdutos() {
    return produtos;
  }

  public void setProdutos(List<ItemDePedidoRequest> produtos) {
    this.produtos = produtos;
  }
}
