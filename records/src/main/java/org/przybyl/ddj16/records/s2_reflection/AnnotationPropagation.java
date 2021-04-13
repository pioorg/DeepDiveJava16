/*
 *
 *  Copyright (C) 2021 Piotr Przybył
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */
package org.przybyl.ddj16.records.s2_reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;

@Target(ElementType.RECORD_COMPONENT)
@Retention(RetentionPolicy.RUNTIME)
@interface RecordComponentAnnotation {
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldAnnotation {
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {
}

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@interface ParameterAnnotation {
}

@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
@interface ConstructorAnnotation {
}

@Target(value = {ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@interface FatAnnotation {
}

public class AnnotationPropagation {
    public static void main(String[] args) {
        var klazz = Kawia.class;
        annotationsOf(klazz);
        annotationsOf(klazz.getRecordComponents());
        annotationsOf(klazz.getDeclaredFields());
        annotationsOf(klazz.getDeclaredMethods());
        annotationsOf(klazz.getDeclaredConstructors());
        System.out.println("Constructor parameter annotations:");
        for (Annotation[] pas : klazz.getDeclaredConstructors()[0].getParameterAnnotations()) {
            for (Annotation pa : pas) {
                System.out.println("    " + pa);
            }
        }
    }

    public static void annotationsOf(AnnotatedElement... aes) {
        for (AnnotatedElement ae : aes) {
            System.out.println("Annotations of " + ae + ":");
            for (Annotation a : ae.getDeclaredAnnotations()) {
                System.out.println("    " + a);
            }
        }
    }

}

@FatAnnotation
// @ConstructorAnnotation // this requires explicit constructor
record Kawia(
    @RecordComponentAnnotation @FieldAnnotation @MethodAnnotation @ParameterAnnotation @FatAnnotation int component) {

//    @ConstructorAnnotation
//    Kawia(@ParameterAnnotation @FatAnnotation int component) {
//        this.component = component;
//    }
}