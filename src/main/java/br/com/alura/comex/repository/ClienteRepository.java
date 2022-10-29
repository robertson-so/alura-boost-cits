package br.com.alura.comex.repository;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.ClienteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  @Query(value = """
      select cli.id,
      cli.nome,
      cli.cpf,
      cli.telefone,
      concat(cli.cidade, '/', cli.estado) as local
      from Cliente cli
      """)
  Page<ClienteProjection> findAllProjection(Pageable pageable);

}
