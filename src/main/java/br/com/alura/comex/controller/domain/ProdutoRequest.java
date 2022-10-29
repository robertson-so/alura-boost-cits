package br.com.alura.comex.controller.domain;

import br.com.alura.comex.validator.ExistsCategoria;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public final class ProdutoRequest {

  @NotEmpty
  private String nome;

  private String descricao;

  @NotNull
  @Positive
  private BigDecimal precoUnitario;

  @NotNull
  @Positive
  private Integer quantidadeEstoque;

  @NotNull
  @ExistsCategoria
  private Long idCategoria;

  public ProdutoRequest() {
    //
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public Integer getQuantidadeEstoque() {
    return quantidadeEstoque;
  }

  public Long getIdCategoria() {
    return idCategoria;
  }
}
