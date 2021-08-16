/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.math.BigInteger;

/**
 *
 * @author hoang
 */
public class BaseNumberController {

    public String convertBinaryToDecimal(String binary) {
        BigInteger decimal = new BigInteger("0");
        BigInteger bin = new BigInteger(binary);
        BigInteger base = new BigInteger("2");
        for (int i = 0; i < binary.length(); i++) {
            BigInteger temp = bin.remainder(new BigInteger("10"));
            decimal = decimal.add(temp.multiply(base.pow(i)));
            bin = bin.divide(new BigInteger("10"));
        }
        return decimal.toString();
    }

    public String convertBinaryToHexa(String binary) {
        String decimal = convertBinaryToDecimal(binary);
        String hexa = convertDecimalToHexa(decimal);
        return hexa;
    }

    public String convertHexaToDecimal(String hexadecimal) {
        String hexDigits = "0123456789ABCDEF";
        hexadecimal = hexadecimal.toUpperCase();
        BigInteger dec = new BigInteger("0");
        BigInteger base = new BigInteger("16");
        int j = 0;
        for (int i = hexadecimal.length()-1; i >= 0; i--)
        {
            char ch = hexadecimal.charAt(i);
            BigInteger n = new BigInteger(String.valueOf(hexDigits.indexOf(ch)));
            dec = dec.add(n.multiply(base.pow(j)));
            j++;
        }
        return dec.toString();
    }

    public String convertHexaToBinary(String hexadecimal) {
        String dec = convertHexaToDecimal(hexadecimal);
        String bin = convertDecimalToBinary(dec);
        return bin;
    }

    public String convertDecimalToBinary(String decimal) {
        BigInteger dec = new BigInteger(decimal);
        BigInteger i = new BigInteger("2");
        String result = "";
        if(dec.intValue() == 0){
            return "0";
        }
        while(dec.compareTo(new BigInteger("0")) == 1){
            BigInteger temp = dec.remainder(i);
            int rem = temp.intValue();
            result = String.valueOf(rem) + result;
            dec = dec.divide(i);
        }
        return result;
    }

    public String convertDecimalToHexa(String decimal) {
        String hexDigits = "0123456789ABCDEF";
        String hexa = "";
        BigInteger dec = new BigInteger(decimal);
        BigInteger base = new BigInteger("16");
        if(dec.intValue() == 0){
            return "0";
        }
        while (dec.compareTo(new BigInteger("0")) == 1) {
            BigInteger remain = dec.remainder(base);
            hexa = hexDigits.charAt(remain.intValue()) + hexa;
            dec = dec.divide(base);
        }
        return hexa;
    }
    public static void main(String[] args) {
        BaseNumberController v = new BaseNumberController();
//        17361641481138400080
        System.out.println(v.convertDecimalToBinary("0"));
    }
}
