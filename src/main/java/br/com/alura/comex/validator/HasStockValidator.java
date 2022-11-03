package br.com.alura.comex.validator;

import br.com.alura.comex.controller.domain.ItemDePedidoRequest;
import br.com.alura.comex.service.ProdutoService;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HasStockValidator implements ConstraintValidator<HasStock, ItemDePedidoRequest> {

  private final ProdutoService produtoService;

  public HasStockValidator(ProdutoService produtoService) {
    this.produtoService = produtoService;
  }

  @Override
  public void initialize(HasStock constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(ItemDePedidoRequest item, ConstraintValidatorContext context) {
    var result = new AtomicBoolean(false);
    this.produtoService
        .findById(item.getIdProduto())
        .ifPresent(
            produto -> result.set(produto.getQuantidadeEstoque() - item.getQuantidade() > 0));
    return result.get();
  }
}
