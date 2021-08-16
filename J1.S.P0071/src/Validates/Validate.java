/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validates;

import java.text.SimpleDateFormat;
import java.util.Date;
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
                int result = Integer.parseInt(in.nextLine().trim());
                if(result < min || result > max){
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }
    
    public double inputDoubleLimit(String msg, String err, double min, double max) {
        while (true) {
            try {
                System.out.print(msg);
                double result = Double.parseDouble(in.nextLine().trim());
                if(result < min || result > max){
                    throw new NumberFormatException();
                }if(result %0.5 !=0){
                    throw  new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }
    
    public String inputString(String msg,String err,String regex){
        String result = null;
        while(true){
            System.out.print(msg);
            result = in.nextLine().trim();
            if(!result.matches(regex)){
                System.err.println(err);
            }
            else return result;
                
        }
    }
    
    public String inputDate(String msg, String err, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        while (true) {
            try {
                System.out.print(msg);
                String result = in.nextLine().trim();
                Date date = sdf.parse(result);
                return sdf.format(date);
            } catch (Exception e) {
                System.err.println(err);
            }
        }
    }
}
