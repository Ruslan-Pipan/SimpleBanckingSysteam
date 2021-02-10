package service.dispatcher.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation needs to mark all methods that responsible for Post request.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Post {
    String value();
}
