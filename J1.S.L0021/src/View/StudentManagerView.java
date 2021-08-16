/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.studentManager;
import Model.Student;
import Validates.Validate;
import java.util.ArrayList;
import java.util.Scanner;
import jdk.nashorn.internal.parser.Lexer;

/**
 *
 * @author hoang
 */
public class StudentManagerView {

    private Scanner in = new Scanner(System.in);

    public void menuView() {
        ArrayList<Student> listStudents = new ArrayList<>();
        listStudents.add(new Student("HE141054", "Nguyen Van B", 6, 1));
        listStudents.add(new Student("HE141054", "Nguyen Van C", 5, 2));
        listStudents.add(new Student("HE141054", "Nguyen Van A", 4, 3));
        while (true) {
            Validate validate = new Validate();
            System.out.println("======== Student Manager ========");
            System.out.println(" 1.	Create");
            System.out.println(" 2.	Find and Sort");
            System.out.println(" 3.	Update/Delete");
            System.out.println(" 4.	Report");
            System.out.println(" 5.	Exit");
            int choice = validate.inputIntLimit("Enter your choice: ",
                    "Please input number in rage [1,5]", 1, 5, false);
            switch (choice) {
                case 1:
                    createStudentView(listStudents);
                    break;
                case 2:
                    searchAndSortView(listStudents);
                    break;
                case 3:
                    updateAndDeleteView(listStudents);
                    break;
                case 4:
                    break;
                case 5:
                    return;
            }

        }
    }

    public void createStudentView(ArrayList<Student> listStudents) {
        Validate validate = new Validate();
        studentManager stManager = new studentManager();
        System.out.println("======== Create Student ========");
        int count = 0;
        boolean continues = true;
        while (continues) {
            count++;
            System.out.println("\nStudent " + count + ": ");
            String id = validate.inputString("ID: ", "Invalid format(HE123456/SE123456)!",
                    "(SE|HE)[0-9]{6}", false);
            String name = validate.inputString("Name: ", "Invalid name!",
                    "[a-zA-Z ]+", false);
            int semester = validate.inputIntLimit("Semester(1-9): ",
                    "Invalid semester!", 1, 9, false);
            int course = validate.inputIntLimit("Course(1.Java|2.Net|3.C/C++):",
                    "Invalid Course", 1, 3, false);

            //Check exist infor
            if (validate.isExistStudent(listStudents, id, name, semester, course)) {
                System.out.println("Already exist information of this student");
                count--;
                continue;
            }
            validate.isExistStudent(listStudents, id, name, semester, course);
            stManager.createStudent(listStudents, id, name, semester, course);

            //check count
            if (count >= 1) {
                String choice = validate.inputString("Do you want to continues(Y/N)?: ",
                        "Please input Y or N!", "[yYnN]", false);
                continues = validate.checkYesNo(choice);
            }

        }
        System.out.println("");
    }

    public void searchAndSortView(ArrayList<Student> listSt) {
        if (listSt.isEmpty()) {
            System.out.println("No student information yet.");
            return;
        }
        studentManager stManager = new studentManager();
        Validate validate = new Validate();
        String searchValue = validate.inputString("Name: ", "Invalid student name!", "[a-zA-Z ]+", false);
        ArrayList<Student> listSearch = stManager.searchStudents(listSt, searchValue);
        if (listSearch.isEmpty()) {
            System.out.println("Name not found ");
            System.out.println(listSt.get(0).getName());
            return;
        }
        stManager.sortByName(listSearch);
        System.out.printf("%-15s%-10s%-15s\n", "Student name", "semester", "Course Name");
        //loop from first to last element of list student
        for (Student student : listSearch) {
            System.out.printf("%-15s%-10s%-15s\n", student.getName(), student.getSemester(),
                    student.getCourseName());
        }

    }

    public void updateAndDeleteView(ArrayList<Student> listSt) {
        Validate validate = new Validate();
        studentManager stManager = new studentManager();
        System.out.println("======= Update And Delete ========");
        String id = validate.inputString("ID: ", "Invalid format(HE123456/SE123456)!",
                "(SE|HE)[0-9]{6}", false);
        ArrayList<Student> listSearch = stManager.getStudentById(listSt, id);
        if (listSearch.isEmpty()) {
            System.out.println("Not found!");
            return;
        }
        System.out.printf("%-5s%-15s%-5s%-10s\n", "", "Name", "Semester", "Course");
        int i = 0;
        for (Student student : listSearch) {
            if (id.toLowerCase().equalsIgnoreCase(student.getId())) {
                ++i;
                System.out.printf("%-5s%-15s%-15s%-5s%-10s\n", i,
                        student.getName(), student.getSemester(), student.getCourseName());
            }
        }
        int select = validate.inputIntLimit("Select student: ", "Please input [1-" + i + "]", 1, i, false);
        String choice = validate.inputString("Do you want to update (U) or delete (D) student?: ",
                "Please input U/D!", "[uUdD]", false);
        if (choice.toUpperCase().equals("U")) {
            listSt.
        } else {
            stManager.deleteStudent(listSt, id);
            System.out.println("Delete successful!");
        }

    }

    public static void main(String[] args) {
        StudentManagerView stView = new StudentManagerView();
        stView.menuView();
    }

}
