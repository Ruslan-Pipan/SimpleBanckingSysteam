package service.dispatcher.annotations;


import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * This annotation needs to mark all controllers that you use.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
@Retention(RUNTIME)
public @interface Controler {
    String value() default "/";
}
