package br.com.alura.comex.validator;

import br.com.alura.comex.controller.domain.PedidoRequest;
import br.com.alura.comex.service.ClienteService;
import br.com.alura.comex.service.ProdutoService;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class PedidoRequestValidator implements ConstraintValidator<ValidPedido, PedidoRequest> {

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private ClienteService clienteService;

  @Override
  public void initialize(ValidPedido constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(PedidoRequest pedido, ConstraintValidatorContext context) {
    var result = new AtomicBoolean(false);
    clienteService.findById(pedido.getIdCliente())
        .ifPresentOrElse(cliente ->
                result.set(
                    pedido.getProdutos()
                        .parallelStream()
                        .allMatch(p -> produtoService.existsById(p.getIdProduto()))),
            () -> result.set(false));

    return result.get();
  }
}
