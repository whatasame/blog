package com.github.whatasame.java.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FoodName {

    String value() default "";

    String english() default "";
}
