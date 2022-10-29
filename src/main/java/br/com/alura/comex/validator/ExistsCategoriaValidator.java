package br.com.alura.comex.validator;

import br.com.alura.comex.service.CategoriaService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsCategoriaValidator implements ConstraintValidator<ExistsCategoria, Long> {

  private final CategoriaService categoriaService;

  public ExistsCategoriaValidator(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @Override
  public void initialize(ExistsCategoria constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Long id, ConstraintValidatorContext context) {
    return this.categoriaService
        .existsById(id);
  }
}
