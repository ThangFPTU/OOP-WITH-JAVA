/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CalculatorController;
import Validates.Validate;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class CalculatorView {

    public void displayMenu() {
        System.out.println("======== Calculator Program ========");
        System.out.println("1. Addition Matrix");
        System.out.println("2. Subtraction Matrix");
        System.out.println("3. Multiplication Matrix");
        System.out.println("4. Quit");

    }

    public void menuView() {
        Validate validate = new Validate();

        while (true) {
            displayMenu();
            int choice = validate.inputIntLimit("Your choice: \n",
                    "Input must in range[1-4]!", 1, 4);
            switch (choice) {
                case 1:
                    CaculationView("Addition", "+");
                    break;
                case 2:
                    CaculationView("Subtraction", "-");
                    break;
                case 3:
                    CaculationView("Multiplication", "x");
                    break;
                case 4:
                    return;
            }
        }
    }

    public int[][] inputMatrix(int n) {

        Validate validate = new Validate();
        int row = validate.inputIntLimit("Enter Row Matrix " + n + ": ", "Row must be a number!", 1, Integer.MAX_VALUE);
        int col = validate.inputIntLimit("Enter Colum Matrix" + n + ": ", "Column must be a number!", 1, Integer.MAX_VALUE);
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = validate.inputIntLimit("Enter Matrix" + n + "[" + i + "]" + "[" + j + "]:",
                        "Values of matrix must be the number!", Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }
        return matrix;
    }

    public void displayMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("[" + matrix[i][j] + "]");
            }
            System.out.println("");
        }
    }

    public void CaculationView(String tittle, String calculation) {
        System.out.println("\n-------- " + tittle + " --------");
        CalculatorController control = new CalculatorController();
        int[][] matrix1 = inputMatrix(1);
        int[][] matrix2 = inputMatrix(2);
        int[][] result = null;
        switch (calculation) {
            case "+":
                result = control.additionMatrix(matrix1, matrix2);
                break;
            case "x":
                result = control.multiplicationMatrix(matrix1, matrix2);
                break;
            case "-":
                result = control.subtractionMatrix(matrix1, matrix2);
                break;

        }if(result == null){
            System.out.println("Cannot calculater!");
        }else{
                    displayMatrix(matrix1);
        System.out.println(calculation);
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(result);
        }
    }

    public static void main(String[] args) {
        CalculatorView cal = new CalculatorView();
        cal.menuView();
    }
}
