package br.com.alura.comex.validator;

import br.com.alura.comex.model.PedidoRequest;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class PedidoRequestValidator implements ConstraintValidator<ValidPedido, PedidoRequest> {

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public void initialize(ValidPedido constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(PedidoRequest pedido, ConstraintValidatorContext context) {
    var result = new AtomicBoolean(false);
    clienteRepository.findById(pedido.getIdCliente())
        .ifPresentOrElse(cliente ->
        {
          result.set(true);
        }, () -> {
          result.set(false);
        });

    return result.get();
  }
}
