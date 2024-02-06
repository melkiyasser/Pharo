package Sparse;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SparseTest {

    @Test
    public void testCalculateSparsity() {
        int[][] matrix = {
                {1, 0, 7, 0, 0},
                {1, 0, 0, 0, 0},
                {2, 0, 5, 0, 0},
                {0, 8, 0, 4, 0}
        };

        double expectedSparsity = 0.35; // Calculated manually

        double result = Sparse.calculateSparsity(matrix);

        assertEquals(expectedSparsity, result, 0.01); // Adjust delta based on precision needed
    }
    @Test
    public void testConvertToSparseRepresentation() {
        int[][] matrix = {
                {1, 0, 7, 0, 0},
                {1, 0, 0, 0, 0},
                {2, 0, 5, 0, 0},
                {0, 8, 0, 4, 0}
        };

        int[][] expectedSparseRepresentation = {
                {4, 0, 0, 1, 2, 2, 3, 3},
                {5, 0, 2, 0, 0, 2, 1, 3},
                {7, 1, 7, 1, 2, 5, 8, 4}
        };

        int[][] result = Sparse.convertToSparseRepresentation(matrix);

        assertArrayEquals(expectedSparseRepresentation, result);
    }

    @Test
    public void testReconstructMatrix() {
        int[][] sparseRepresentation = {
                {4, 0, 0, 1, 2, 2, 3, 3},
                {5, 0, 2, 0, 0, 2, 1, 3},
                {7, 1, 7, 1, 2, 5, 8, 4}
        };

        int[][] expectedMatrix = {
                {1, 0, 7, 0, 0},
                {1, 0, 0, 0, 0},
                {2, 0, 5, 0, 0},
                {0, 8, 0, 4, 0}
        };

        int[][] result = Sparse.reconstructMatrix(sparseRepresentation);

        assertArrayEquals(expectedMatrix, result);
    }
    @Test
    public void testAllZerosMatrix() {
        int[][] matrix = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        double sparsity = Sparse.calculateSparsity(matrix);

        assertEquals(0.0, sparsity, 0.01); // The sparsity of an all-zeros matrix should be 0
    }
    @Test
    public void testSquareMatrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        double sparsity = Sparse.calculateSparsity(matrix);

        assertEquals(1, sparsity, 0.01); // Adjusted expected sparsity based on the square matrix
    }


}
