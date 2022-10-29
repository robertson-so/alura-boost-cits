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
@Constraint(validatedBy = ExistsCategoriaValidator.class)
@Documented
public @interface ExistsCategoria {

  String message() default "{produto.categoria.not.found}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
