package br.com.alura.comex.controller.domain;

import br.com.alura.comex.model.ClienteProjection;

public final class ClienteProjectionResponse {

  private final Long id;
  private final String nome;
  private final String cpf;
  private final String telefone;
  private final String local;

  public ClienteProjectionResponse(ClienteProjection clienteProjection) {
    this.id = clienteProjection.getId();
    this.nome = clienteProjection.getNome();
    this.cpf = clienteProjection.getCPF();
    this.telefone = clienteProjection.getTelefone();
    this.local = clienteProjection.getLocal();
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getCPF() {
    return cpf;
  }

  public String getTelefone() {
    return telefone;
  }

  public String getLocal() {
    return local;
  }
}
