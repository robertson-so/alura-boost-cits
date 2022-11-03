package br.com.alura.comex.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

  @Serial
  private static final long serialVersionUID = 5860099873056901749L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  private LocalDate data = LocalDate.now();

  @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "cliente_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
  private Cliente cliente;

  private BigDecimal desconto = BigDecimal.ZERO;

  @Enumerated(EnumType.STRING)
  private TipoDesconto tipoDesconto = TipoDesconto.NENHUM;

  @OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<ItemDePedido> itens = new ArrayList<>();

  public Pedido() {
    //
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }

  public void setDesconto(BigDecimal desconto) {
    this.desconto = desconto;
  }

  public TipoDesconto getTipoDesconto() {
    return tipoDesconto;
  }

  public void setTipoDesconto(TipoDesconto tipoDesconto) {
    this.tipoDesconto = tipoDesconto;
  }

  public List<ItemDePedido> getItens() {
    return itens;
  }

  public void setItens(List<ItemDePedido> itens) {
    this.itens = itens;
  }
}
