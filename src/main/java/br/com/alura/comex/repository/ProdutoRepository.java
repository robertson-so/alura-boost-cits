package br.com.alura.comex.repository;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.ProdutoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  @Query(value = """
      select prod.id as idProduto,
      prod.nome,
      prod.precoUnitario as preco,
      prod.descricao,
      prod.quantidadeEstoque,
      cat.id as idCategoria,
      cat.nome as nomeCategoria
      from Produto prod
      left join prod.categoria cat
      """)
  Page<ProdutoProjection> findAllProjection(Pageable pageable);

}
