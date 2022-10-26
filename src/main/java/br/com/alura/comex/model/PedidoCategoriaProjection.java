package br.com.alura.comex.model;

import java.math.BigDecimal;

public interface PedidoCategoriaProjection {

  String getCategoria();

  Integer getQuantidadeProdutosVendidos();

  BigDecimal getMontanteVendido();

}
