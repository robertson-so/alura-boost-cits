package br.com.alura.comex.validator;

import br.com.alura.comex.service.ClienteService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.context.annotation.Lazy;

public class ExistsClienteValidator implements ConstraintValidator<ExistsCliente, Long> {

  private final ClienteService clienteService;

  public ExistsClienteValidator(@Lazy ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @Override
  public void initialize(ExistsCliente constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Long idCliente, ConstraintValidatorContext context) {
    return this.clienteService.existsById(idCliente);
  }
}
