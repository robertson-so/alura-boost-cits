package br.com.alura.comex.service;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.PedidoCategoriaProjection;
import br.com.alura.comex.controller.domain.PedidoRequest;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.TipoDesconto;
import br.com.alura.comex.repository.PedidoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

  private final PedidoRepository pedidoRepository;
  private final ClienteService clienteService;
  private final ProdutoService produtoService;

  public PedidoService(
      @Lazy PedidoRepository pedidoRepository,
      @Lazy ClienteService clienteService,
      @Lazy ProdutoService produtoService) {
    this.pedidoRepository = pedidoRepository;
    this.clienteService = clienteService;
    this.produtoService = produtoService;
  }

  public Page<PedidoCategoriaProjection> findVendidos(Pageable pageable) {
    return this.pedidoRepository.findVendidos(pageable);
  }

  public void add(PedidoRequest request) {
    clienteService.findById(request.getIdCliente())
        .ifPresentOrElse(cliente -> {
          var products = new ArrayList<Produto>();
          var possibleToBuy = request.getProdutos().stream().allMatch(itemRequest -> {
            var hasStock = new AtomicBoolean(false);
            produtoService.findById(itemRequest.getIdProduto())
                .ifPresent(produto -> {
                  hasStock.set(produto.getQuantidadeEstoque() - itemRequest.getQuantidade() > 0);
                  products.add(produto);
                });
            return hasStock.get();
          });
          if (possibleToBuy) {
            var pedido = new Pedido();
            pedido.setCliente(cliente);
            if (pedidoRepository.hasMinimumOrders(cliente, 5L)) {
              pedido.setTipoDesconto(TipoDesconto.FIDELIDADE);
              pedido.setDesconto(new BigDecimal("0.05"));
            }
          }
          throw new RuntimeException("Estoque insuficiente para algum(ns) do(s) produto(s).");
        }, () -> {
          throw new RuntimeException("Cliente não encontrado.");
        });
  }
}
