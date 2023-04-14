package com.octo.parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Henri Tremblay
 */
public class MatrixComputation {

    public static double[][] matrixMultiplication(double[][] matA, double[][] matB) {
        double[][] result = new double[matA.length][matB[0].length];
        int matARows = matA.length;
        int matBCols = matB[0].length;
        int matACols = matB.length;

        for (int i = 0; i < matARows; i++) {
            double[] row = matA[i];
            for (int j = 0; j < matBCols; j++) {
                double temp = 0;
                for (int k = 0; k < matACols; k++) {
                    temp += row[k] * matB[k][j];
                }
                result[i][j] = temp;
            }
        }

        int i = 0;
        Consumer<String> f = p -> System.out.println(i);
        //i += i;
        return result;
    }

    public static double[][] parallelMatrixMultiplication(double[][] matA, double[][] matB) {
        double[][] result = new double[matA.length][matB[0].length];
        int matARows = matA.length;
        int matBCols = matB[0].length;
        int matACols = matB.length;

        class Tuple {
            double[] rowA;
            double[] rowResult;

            Tuple(double[] rowA, double[] rowResult) {
                this.rowA = rowA;
                this.rowResult = rowResult;
            }
        }

        Tuple[] rows = new Tuple[matARows];
        for (int i = 0; i < matARows; i++) {
            rows[i] = new Tuple(matA[i], result[i]);
        }

        Arrays.stream(rows).parallel().forEach(row -> {
           for (int j = 0; j < matBCols; j++) {
              double temp = 0;
              for (int k = 0; k < matACols; k++) {
                 temp += row.rowA[k] * matB[k][j];
              }
              row.rowResult[j] = temp;
           }
        });

       return result;


    }

   public void foo() {
      final List<String> l = new ArrayList<>();
      for (final String s : l) {
         System.out.println(s);
      }
   }
}
