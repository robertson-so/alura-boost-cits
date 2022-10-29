package br.com.alura.comex.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "categorias", uniqueConstraints = {
    @UniqueConstraint(name = "categorias_nome_uq", columnNames = {"nome"})
})
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String nome;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StatusCategoria status = StatusCategoria.ATIVA;

  @OneToMany(mappedBy = "categoria")
  private List<Produto> produtos = new ArrayList<>();

  public Categoria() {
    //
  }

  public void adicionarProduto(Produto produto) {
    produto.setCategoria(this);
    this.produtos.add(produto);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public StatusCategoria getStatus() {
    return status;
  }

  public void setStatus(StatusCategoria status) {
    this.status = status;
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

  public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
  }
}
