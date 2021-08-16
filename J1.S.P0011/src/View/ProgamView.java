/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BaseNumberController;
import Validate.Validate;
import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class ProgamView {
//

    private Scanner in = new Scanner(System.in);
    BaseNumberController control = new BaseNumberController();
//

    public void menuView() {
        Validate validate = new Validate();
        while (true) {
            menu();
            int choice = validate.inputIntLimit("Your choice: ",
                    "Input must in range[" + 1 + "," + 4 + "]", 1, 4);
            String convertTo = "";
            switch (choice) {
                case 1:
                    String binary = validate.inputString("Binary: ", "[0-1]+", "Invalid binary number!");
                    convertTo = ConvertToView("binary", "decimal", "hexadecimal");
                    convertView(binary, "binary", convertTo);
                    break;
                case 2:
                    String decimal = validate.inputString("Decimal: ", "[0-9]+", "Invalid decimal number!");
                    convertTo = ConvertToView("decimal", "binary", "hexadecimal");
                    convertView(decimal, "decimal", convertTo);
                    break;
                case 3:
                    String hexa = validate.inputString("Hexadecimal: ", "[0-9a-fA-F]+", "Invalid hexadecimal number!");
                    convertTo = ConvertToView("hexadecimal", "binary", "decimal");
                    convertView(hexa, "hexadecimal", convertTo);
                    break;
                case 4:
                    return;
            }
        }
    }

    public void menu() {
        System.out.println("\n======== Change Base Number System ========");
        System.out.println("1. Convert From Binary.");
        System.out.println("2. Convert From Decimal.");
        System.out.println("3. Convert From Hexa.");
        System.out.println("4. Exit");
    }

    public String ConvertToView(String from, String toCase1, String toCase2) {
        Validate validate = new Validate();
        System.out.println("1. Convert form " + from + " to " + toCase1);
        System.out.println("2. Convert form " + from + " to " + toCase2);
        int choice = validate.inputIntLimit("Enter your choice: ",
                "Input must in range[" + 1 + "," + 4 + "]", 1, 2);
        if (choice == 1) {
            return toCase1;
        } else {
            return toCase2;
        }
    }
//

    public void convertView(String baseNumber, String convertFrom, String convertTo) {
        String convertType = convertFrom + " " + convertTo;
        String result = "";
        switch (convertType) {
            case "binary decimal":
                result = control.convertBinaryToDecimal(baseNumber);
                break;
            case "binary hexadecimal":
                result = control.convertBinaryToHexa(baseNumber);
                break;
            case "decimal binary":
                result = control.convertDecimalToBinary(baseNumber);
                break;
            case "decimal hexadecimal":
                result = control.convertDecimalToHexa(baseNumber);
                break;
            case "hexadecimal binary":
                result = control.convertHexaToBinary(baseNumber);
                break;
            case "hexadecimal decimal":
                result = control.convertHexaToDecimal(baseNumber);
                break;
        }
        System.out.println(baseNumber + " convert to " + convertTo + " is: " + result);
    }
  
    public static void main(String[] args) {
        ProgamView progamView = new ProgamView();
        progamView.menuView();
    }
}
