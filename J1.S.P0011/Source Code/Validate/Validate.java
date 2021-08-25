/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;

import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Validate {
private Scanner in = new Scanner(System.in);
    public int inputIntLimit(String msg, String err, int min, int max) {
        //loop until user input correct
        while (true) {
            try {
                System.out.print(msg);
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println(err);
            }
        }
    }
    public String inputString(String msg,String regex,String err) {
        while(true){
            System.out.print(msg);
            String input = in.nextLine().trim();
            if(input.matches(regex)){
                return input;
            }
            System.out.println(err);
        }
    }
 
}
