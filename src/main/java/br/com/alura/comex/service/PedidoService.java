package br.com.alura.comex.service;

import br.com.alura.comex.controller.domain.PedidoCategoriaProjectionResponse;
import br.com.alura.comex.controller.domain.PedidoRequest;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.TipoDesconto;
import br.com.alura.comex.model.TipoDescontoItem;
import br.com.alura.comex.repository.PedidoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.transaction.Transactional;
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

  public Page<PedidoCategoriaProjectionResponse> findVendidos(Pageable pageable) {
    return this.pedidoRepository.findVendidos(pageable).map(PedidoCategoriaProjectionResponse::new);
  }

  @Transactional
  public void add(PedidoRequest request) {
    var client = this.clienteService
        .findById(request.getIdCliente())
        .orElseThrow();
    var pedido = new Pedido();
    pedido.setCliente(client);
    if (pedidoRepository.hasMinimumOrders(client, 5L)) {
      pedido.setTipoDesconto(TipoDesconto.FIDELIDADE);
      pedido.setDesconto(new BigDecimal("0.05"));
    }
    var products = new ArrayList<Produto>();
    request.getProdutos().forEach(itemRequest -> {
      var product = this.produtoService
          .findById(itemRequest.getIdProduto())
          .orElseThrow();
      products.add(product);
      var item = new ItemDePedido();
      item.setPedido(pedido);
      item.setProduto(product);
      item.setPrecoUnitario(product.getPrecoUnitario());
      item.setQuantidade(itemRequest.getQuantidade());
      if (item.getQuantidade() > 10) {
        item.setDesconto(new BigDecimal("0.1"));
        item.setTipoDesconto(TipoDescontoItem.QUANTIDADE);
      }
      pedido.getItens().add(item);
    });
    this.pedidoRepository.saveAndFlush(pedido);
    request.getProdutos().forEach(itemRequest -> {
      var product = products.stream()
          .filter(p -> p.getId().equals(itemRequest.getIdProduto()))
          .findFirst()
          .orElseThrow();
      product.setQuantidadeEstoque(product.getQuantidadeEstoque() - itemRequest.getQuantidade());
      this.produtoService.save(product);
    });
  }
}
