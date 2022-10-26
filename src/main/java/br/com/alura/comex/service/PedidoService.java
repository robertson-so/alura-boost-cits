package br.com.alura.comex.service;

import br.com.alura.comex.model.PedidoCategoriaProjection;
import br.com.alura.comex.model.PedidoRequest;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

  private final PedidoRepository pedidoRepository;

  public PedidoService(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  public Page<PedidoCategoriaProjection> findVendidos(Pageable pageable) {
    return this.pedidoRepository.findVendidos(pageable);
  }

  public void add(PedidoRequest request) {

  }
}
