package br.com.alura.comex.validator;

import br.com.alura.comex.service.ProdutoService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsProdutoValidator implements ConstraintValidator<ExistsProduto, Long> {

  private final ProdutoService produtoService;

  public ExistsProdutoValidator(ProdutoService produtoService) {
    this.produtoService = produtoService;
  }

  @Override
  public void initialize(ExistsProduto constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Long idProduto, ConstraintValidatorContext context) {
    return this.produtoService.existsById(idProduto);
  }
}
