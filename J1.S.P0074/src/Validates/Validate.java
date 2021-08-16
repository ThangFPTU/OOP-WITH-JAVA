/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validates;

import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Validate {
    private Scanner in = new Scanner(System.in);

    public int inputIntLimit(String msg, String err, int min, int max) {
        while (true) {
            try {
                System.out.print(msg);
                String temp = null;
                temp = in.nextLine().trim();
                int result = Integer.parseInt(temp);
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }
}
