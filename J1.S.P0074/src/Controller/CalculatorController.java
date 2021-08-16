/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author hoang
 */
public class CalculatorController {

    public int[][] additionMatrix(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            return null;
        }
        int col = matrix1[0].length;
        int row = matrix1.length;
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public int[][] subtractionMatrix(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            System.err.println("Cannot subtract");
            return null;
        }
        int col = matrix1[0].length;
        int row = matrix1.length;
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    public int[][] multiplicationMatrix(int[][] matrix1, int[][] matrix2) {
        if (matrix1[0].length != matrix2.length) {
            System.err.println("Cannot multiple!");
            return null;
        }
        int row1 = matrix1.length;
        int col1 = matrix1[0].length;
        int row2 = matrix2.length;
        int col2 = matrix2[0].length;
        int[][] result = new int[row1][col2];
        for (int row = 0; row < row1; row++) {
            for (int col = 0; col < col2; col++) {
                result[row][col] = 0;
                for (int general = 0; general < col1; general++) {
                    result[row][col] += matrix1[col][general] * matrix2[general][col];
                }
            }
        }
        return result;
    }

}
