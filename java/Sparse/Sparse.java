package Sparse;

class Sparse {
    public static void main(String[] args) {
        int[][] denseMatrix = {
                {1, 0, 7, 0, 0},
                {1, 0, 0, 0, 0},
                {2, 0, 5, 0, 0},
                {0, 8, 0, 4, 0}
        };

        int rows = denseMatrix.length;
        int cols = denseMatrix[0].length;

        double sparsityThreshold = 0.5; // Adjust as needed

        if (calculateSparsity(denseMatrix) < sparsityThreshold) {
            // Convert the matrix to sparse representation
            int[][] representsparse = convertToSparseRepresentation(denseMatrix);

            System.out.println("Original Matrix:");
            printMatrix(denseMatrix);

            System.out.println("\nSparse Representation:");
            printSparseRepresentation(representsparse);

            // Reconstruct the matrix from sparse representation
            int[][] reconstructedMatrix = reconstructMatrix(representsparse);

            System.out.println("\nReconstructed Matrix:");
            printMatrix(reconstructedMatrix);
        } else {
            System.out.println("Sparse representation is not needed.");
            System.out.println("Original Matrix:");
            printMatrix(denseMatrix);
        }
    }

    public static double calculateSparsity(int[][] matrix) {
        int totalElements = matrix.length * matrix[0].length;
        int nonZeroElements = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    nonZeroElements++;
                }
            }
        }

        return (double) nonZeroElements / totalElements;
    }

    public static int[][] convertToSparseRepresentation(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int s = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (matrix[i][j] != 0)
                    s++;

        int[][] representsparse = new int[3][s + 1];  // Corrected to s + 1
        representsparse[0][0] = rows;
        representsparse[1][0] = cols;
        representsparse[2][0] = s;  // Corrected to store the number of non-zero values
        int k = 1;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (matrix[i][j] != 0) {
                    representsparse[0][k] = i;
                    representsparse[1][k] = j;
                    representsparse[2][k] = matrix[i][j];
                    k++;
                }

        return representsparse;
    }



    public static int[][] reconstructMatrix(int[][] sparseRepresentation) {
        int rows = sparseRepresentation[0][0];
        int cols = sparseRepresentation[1][0];
        int nonZeroValues = sparseRepresentation[2][0];

        int[][] reconstructedMatrix = new int[rows][cols];

        // Iterate through the sparse representation to fill in the matrix
        for (int i = 1; i <= nonZeroValues; i++) {  // Corrected the loop condition
            int row = sparseRepresentation[0][i];
            int col = sparseRepresentation[1][i];
            int value = sparseRepresentation[2][i];

            // Check if row and col are within the valid range of reconstructedMatrix
            if (row >= 0 && row < rows && col >= 0 && col < cols) {
                reconstructedMatrix[row][col] = value;
            } else {
                // Handle the case where row or col is out of bounds
                System.err.println("Warning: Ignoring invalid indices (" + row + ", " + col + ")");
                // You can choose to throw an exception or take other appropriate action
            }
        }

        return reconstructedMatrix;
    }



    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printSparseRepresentation(int[][] sparseRepresentation) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < sparseRepresentation[0].length; j++) {
                System.out.print(sparseRepresentation[i][j] + " ");
            }
            System.out.println();
        }
    }
}
