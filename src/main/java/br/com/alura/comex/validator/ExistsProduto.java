package br.com.alura.comex.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsProdutoValidator.class)
@Documented
public @interface ExistsProduto {

  String message() default "{pedido.produto.not.found}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
