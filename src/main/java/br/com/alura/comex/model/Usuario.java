package br.com.alura.comex.model;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

  @Serial
  private static final long serialVersionUID = -8364732156567938854L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @OneToOne(optional = false, orphanRemoval = true)
  @JoinColumn(name = "cliente_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_usuario_cliente"))
  private Cliente cliente;

  @Email
  @NotEmpty
  @Column(nullable = false)
  private String email;

  @NotEmpty
  @Column(nullable = false)
  private String senha;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Perfil> perfis = new ArrayList<>();

  public Usuario() {
    //
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public List<Perfil> getPerfis() {
    return perfis;
  }

  public void setPerfis(List<Perfil> perfis) {
    this.perfis = perfis;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.perfis;
  }

  @Override
  public String getPassword() {
    return this.senha;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
