package br.com.alura.comex.repository;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.PedidoCategoriaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

  @Query(value = """
      select cat.nome as categoria,
      (select sum(item.quantidade)
       from ItemDePedido item
       left join item.produto prod
       where prod.categoria = cat) as quantidadeProdutosVendidos,
      (select sum(item.precoUnitario * item.quantidade)
       from ItemDePedido item
       left join item.produto prod
       where prod.categoria = cat) as montanteVendido
      from Categoria cat
      """, countQuery = """
            select count(cat.nome) as categoria,
            (select sum(item.quantidade)
             from ItemDePedido item
             left join item.produto prod
             where prod.categoria = cat) as quantidadeProdutosVendidos,
            (select sum(item.precoUnitario * item.quantidade)
             from ItemDePedido item
             left join item.produto prod
             where prod.categoria = cat) as montanteVendido
            from Categoria cat
      """)
  Page<PedidoCategoriaProjection> findVendidos(Pageable pageable);

  @Query(value = """
      select count(ped.id) > :quantity from Pedido ped
      where ped.cliente = :cliente
      """)
  Boolean hasMinimumOrders(Cliente cliente, Long quantity);
}
