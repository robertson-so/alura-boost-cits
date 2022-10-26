package br.com.alura.comex.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PedidoRequestValidator.class)
@Documented
public @interface ValidPedido {

  String message() default "O Pedido não é válido.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
