/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validates;

import Model.Student;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Validate {

    private Scanner in = new Scanner(System.in);

    public int inputIntLimit(String msg, String err, int min, int max, boolean allowNull) {
        while (true) {
            try {
                System.out.print(msg);
                String temp = null;
                temp = in.nextLine().trim();
                if (allowNull == true) {
                    if (temp.isEmpty()) {
                        return 0;
                    }
                }
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

    public String inputString(String msg, String err, String regex, boolean allowNull) {
        String result = null;
        while (true) {
            System.out.print(msg);
            result = in.nextLine().trim();
            if (allowNull == true) {
                if (result.isEmpty()) {
                    return "";
                }
            }
            if (!result.matches(regex)) {
                System.err.println(err);
            } else {
                return result;
            }

        }
    }

    public boolean isExistStudent(ArrayList<Student> ls, String id,
            String studentName, int semester, int course) {
        int size = ls.size();
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())
                    && studentName.toLowerCase().equalsIgnoreCase(student.getName().toLowerCase())
                    && semester == student.getSemester()
                    && course == student.getCourse()) {
                return true;
            }
        }
        return false;
    }
    

    public boolean checkYesNo(String choice) {
        if (choice.toUpperCase().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

}
