package br.com.alura.comex.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemDePedido implements Serializable {

  @Serial
  private static final long serialVersionUID = 7551841639767101713L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(name = "preco_unitario", nullable = false)
  private BigDecimal precoUnitario;

  @Column(nullable = false)
  private Integer quantidade;

  @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "pedido_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_itemdepedido_pedido"))
  private Pedido pedido;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "produto_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_itemdepedido_produto"))
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
