package br.com.alura.comex.repository;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.PedidoCategoriaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

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
}
