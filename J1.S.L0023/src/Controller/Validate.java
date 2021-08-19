/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Fruit;
import java.util.ArrayList;
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
                System.out.print("Try again!");
            }
        }
    }

    public float inputFloatLimit(String msg, String err, float min, float max) {
        while (true) {
            try {
                System.out.print(msg);
                String temp = null;
                temp = in.nextLine().trim();
                float result = Float.parseFloat(temp);
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }

    public String inputString(String msg, String err, String regex) {
        String result = null;
        while (true) {
            System.out.print(msg);
            result = in.nextLine().trim();
            if (!result.matches(regex)) {
                System.err.println(err);
            } else {
                return result;
            }
        }
    }

    public boolean checkYesNo(String choice) {
        if (choice.toUpperCase().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExistedFruitId(ArrayList<Fruit> listFruit, int id) {
        for (Fruit fruit : listFruit) {
            if (fruit.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistedFruitName(ArrayList<Fruit> listFruit, String name) {
        for (Fruit fruit : listFruit) {
            if (fruit.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase())) {
                return true;
            }
        }

        return false;
    }
}
