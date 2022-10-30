package br.com.alura.comex.controller.domain;

import br.com.alura.comex.validator.ExistsCliente;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public final class PedidoRequest {

  @NotEmpty
  @UniqueElements
  private final List<@Valid ItemDePedidoRequest> produtos = new ArrayList<>();
  @NotNull
  @ExistsCliente
  private Long idCliente;

  public PedidoRequest() {
    //
  }

  public Long getIdCliente() {
    return idCliente;
  }

  public List<ItemDePedidoRequest> getProdutos() {
    return produtos;
  }

}
