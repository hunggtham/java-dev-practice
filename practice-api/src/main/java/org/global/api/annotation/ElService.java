package org.global.api.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface ElService {
    @AliasFor(annotation = Service.class)
    String value() default "";

    String key() default "";
}
