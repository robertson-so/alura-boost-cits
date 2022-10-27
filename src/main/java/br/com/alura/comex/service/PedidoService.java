package br.com.alura.comex.service;

import br.com.alura.comex.model.PedidoCategoriaProjection;
import br.com.alura.comex.model.PedidoRequest;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.PedidoRepository;
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
            var canBuy = new AtomicBoolean(false);
            produtoService.findById(itemRequest.getIdProduto())
                .ifPresent(produto -> {
                  canBuy.set(produto.canBuy(itemRequest.getQuantidade()));
                  products.add(produto);
                });
            return canBuy.get();
          });
          if (possibleToBuy) {

          }
          throw new RuntimeException("Estoque insuficiente para algum(ns) do(s) produto(s).");
        }, () -> {
          throw new RuntimeException("Cliente não encontrado.");
        });
  }
}
