package com.octo.parallel;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Henri Tremblay
 */
class MatrixComputationTest {

    double[][] matA = new double[][] {
            {0,1,2},
            {3,4,5}
    };
    double[][] matB = new double[][] {
            {0,1},
            {2,3},
            {4,5}
    };
    double[][] expected = new double[][] {
            {10, 13},
            {28, 40}
    };

    /**
     * [[0,1,2],[3,4,5]]*[[0,1],[2,3],[4,5]]
     * @throws Exception
     */
    @Test
    void testMatrixMultiplication() throws Exception {
        double[][] actual = MatrixComputation.matrixMultiplication(matA, matB);
        checkResult(actual);
    }

    @Test
    void testParallelMatrixMultiplication() throws Exception {
        double[][] actual = MatrixComputation.parallelMatrixMultiplication(matA, matB);
        checkResult(actual);
    }

    private void checkResult(double[][] actual) {
        for (int i = 0; i < actual.length; i++) {
            assertThat(actual[i]).containsExactly(expected[i], Offset.offset(0.1));
        }
    }
}
