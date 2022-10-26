package br.com.alura.comex.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemDePedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "preco_unitario", nullable = false)
  private BigDecimal precoUnitario;

  @Column(nullable = false)
  private Integer quantidade;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Pedido pedido;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Produto produto;

  @Column(nullable = false)
  private BigDecimal desconto = BigDecimal.ZERO;

  @Enumerated(EnumType.STRING)
  private TipoDescontoItem tipoDesconto = TipoDescontoItem.NENHUM;

  public ItemDePedido() {
    //
  }

  public ItemDePedido(Integer quantidade, Produto produto) {
    this.quantidade = quantidade;
    this.produto = produto;
  }

  public BigDecimal getValorTotalItem() {
    return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public void setPrecoUnitario(BigDecimal precoUnitario) {
    this.precoUnitario = precoUnitario;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }

  public void setDesconto(BigDecimal desconto) {
    this.desconto = desconto;
  }

  public TipoDescontoItem getTipoDesconto() {
    return tipoDesconto;
  }

  public void setTipoDesconto(TipoDescontoItem tipoDesconto) {
    this.tipoDesconto = tipoDesconto;
  }
}
