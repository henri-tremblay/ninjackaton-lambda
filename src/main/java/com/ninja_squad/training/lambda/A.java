package com.ninja_squad.training.lambda;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Henri Tremblay
 */
public class A {

   public static void main(String[] args) {
      Method[] methods = A.class.getDeclaredMethods();
      Arrays.stream(methods).forEach(m -> System.out.println(m + " " + m.isBridge() + " " + m.isSynthetic()));
   }
}
