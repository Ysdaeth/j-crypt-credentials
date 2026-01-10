package io.github.ysdaeth.jccredentials.jcc.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation defines field of module for Modular Crypt Format, but it can be
 * used differently for other implementations of
 * {@link io.github.ysdaeth.jccredentials.jcc.common.serializer.Serializer}.
 * By default, it is designed to work with
 * {@link io.github.ysdaeth.jccredentials.jcc.common.serializer.ConfigurableSerializer}
 * Set of annotations must start at 0, and each order must be unique, with no skipped values (must be incremented by 1).
 * If annotated field is final then public all args constructor must be provided, annotated with
 * {@link SerializerCreator} and parameters in the same order as {@link Module#order()}.
 * All args constructor means a constructor with parameters matching with annotated fields.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Module {
    int order();
}
