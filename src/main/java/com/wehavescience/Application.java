package com.wehavescience;

import com.wehavescience.context.SpringContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringContext.class);
        annotationConfigApplicationContext.start();
    }
}
