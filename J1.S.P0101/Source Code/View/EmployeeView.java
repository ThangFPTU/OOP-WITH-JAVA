/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.EmployeeController;
import Model.Employee;
import Validates.Validate;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class EmployeeView {

    EmployeeController control = new EmployeeController();

    public void displayMenu() {
        System.out.println("======== Employee Management ========");
        System.out.println("1. Add Employees");
        System.out.println("2. Update Employees");
        System.out.println("3. Remove Employees");
        System.out.println("4. Search Employees");
        System.out.println("5. Sort Employees by salary");
        System.out.println("6. Exit");
    }

    public void menuView() {
        Validate validate = new Validate();

        while (true) {
            displayMenu();
            int choice = validate.inputIntLimit("Your choice: ",
                    "Input must in range[1-6]!", 1, 6,false);
            switch (choice) {
                case 1:
                    addEmployeeView();
                    break;
                case 2:
                    UpdateEmployeeView();
                    break;
                case 3:
                    RemoveEmployeeView();
                    break;
                case 4:
                    searchEmployeeView();
                    break;
                case 5:
                    ArrayList<Employee> listEmployee = control.sortEmployeeBySalary();
                    showEmployees(listEmployee);
                    break;
                case 6:
                    return;
            }
        }
    }

    public void addEmployeeView() {
        System.out.println("\n-------- Add Employees --------");
        Validate validate = new Validate();
        int id = 0;
        while (true) {
            id = validate.inputIntLimit("Id: ", "Invalid Id!", 1, Integer.MAX_VALUE,false);
            if (validate.checkDuplicateId(id, control.getListEmployees())) {
                System.out.println("Duplicate!");
            } else {
                break;
            }
        }
        String fName = validate.inputString("First Name: ",
                "First name is required!", "[a-zA-Z ]+",false);
        String lName = validate.inputString("Last Name: ",
                "Last name is required!", "[a-zA-Z ]+",false);
        String phone = validate.inputString("Phone: ",
                "Phone is required and is ditgits!", "[0-9]{10,11}",false);
        String Email = validate.inputString("Email: ",
                "Invalid is Email!", "^[\\w]+@([a-z]+\\.[a-z]+|[a-z]+\\.[a-z]+\\.[a-z]+)$",false);
        String Address = validate.inputString("Adrress: ",
                "Adress is required!", "[a-zA-Z ]+",false);
        String dob = validate.inputDate("DOB: ", "Invalid format!", "dd/MM/yyyy",false);
        int boo = validate.inputIntLimit("Sex(1.Male/2.Female): ", "Invalid sex!", 1, 2,false);
        boolean sex = (boo == 1) ? true : false;
        double salary = validate.inputDoubleLimit("Salary: ", "Invalid salary!",
                1, Double.MAX_VALUE,false);
        String egency = validate.inputString("Egency: ", "invalid!", "[a-zA-Z ]+",false);
        boolean condition = control.addEmployee(id, fName, lName, phone, Email, Address, dob, sex, salary, egency);
        if (condition == true) {
            System.out.println("Add successful!");
        } else {
            System.err.println("Add fail!");
        }
    }

    public void UpdateEmployeeView() {
        System.out.println("\n-------- Update Employee --------");
        Validate validate = new Validate();
        int id = 0;
        id = validate.inputIntLimit("Id: ", "Invalid Id!", 0, Integer.MAX_VALUE,false);
        Employee em = null;
        em = control.getEployeeById(id);
        if (em == null) {
            System.out.println("Id not exist!");
            return;
        }
        String fName = validate.inputString("First Name: ",
                "Invalid", "[a-zA-Z ]+",true);
        String lName = validate.inputString("Last Name: ",
                "Invalid", "[a-zA-Z ]+",true);
        String phone = validate.inputString("Phone: ",
                "Invalid", "[0-9]{10,11}",true);
        String Email = validate.inputString("Email: ",
                "Invalid", "^[\\w]+@([a-z]+\\.[a-z]+|[a-z]+\\.[a-z]+\\.[a-z]+)$",true);
        String Address = validate.inputString("Adrress: ",
                "Invalid", "[a-zA-Z ]+",true);
        String dob = validate.inputDate("DOB: ", "Invalid", "dd/MM/yyyy",true);
        int boo = validate.inputIntLimit("Sex(1.Male/2.Female): ", "Invalid", 1, 2,true);
        Boolean sex = null;
        if(boo == 1){
            sex = true;
        }else if(boo == 2){
            sex = false;
        }
        double salary = validate.inputDoubleLimit("Salary: ", "Invalid", 1, Integer.MAX_VALUE,true);
        String egency = validate.inputString("Egency: ", "Invalid!", "[a-zA-Z ]+",true);
        control.updateEmployee(id, fName, lName, phone, Email, Address, dob, sex, salary, egency);
        System.out.println("Update succesfull!");
    }

    public void searchEmployeeView() {
        System.out.println("-------- Search Employee --------");
        Validate validate = new Validate();
        System.out.println("");
        String searchValue = validate.inputString("Enter name: ", "Invalid name!", "[a-zA-Z ]+",true);
        ArrayList<Employee> listEmployee = control.searchEmployeeByName(searchValue);
        if (listEmployee.isEmpty()) {
            System.err.println("Not found!");
            return;
        }
        showEmployees(listEmployee);
    }

    public void RemoveEmployeeView() {
        System.out.println("-------- Remove Employee --------");
        Validate validate = new Validate();
        int id = validate.inputIntLimit("Id: ", "Invalid id!", 0, Integer.MAX_VALUE,false);
        if (control.removeEmployee(id)) {
            System.out.println("Remove successful!");
        } else {
            System.out.println("Not found employee");
        }
    }

    public void showEmployees(ArrayList<Employee> listEmployee) {
        if (listEmployee.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.printf("\n%-5s%-15s%-15s%-15s%-26s%-10s%-12s%-10s%-10s%-10s\n",
                "ID", "First Name", "Last Name", "Phone", "Email", "Adress", "DOB",
                "Sex", "Salary", "Egency");
        for (int i = 0; i < listEmployee.size(); i++) {
            Employee employee = listEmployee.get(i);
            System.out.printf("%-5s%-15s%-15s%-15s%-26s%-10s%-12s%-10s%-10.1f%-10s\n",
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getPhone(),
                    employee.getEmail(),
                    employee.getAddress(),
                    employee.getDob(),
                    employee.isSex()?"Male":"Female",
                    employee.getSalary(),
                    employee.getAgency()
            );
        }
    }

    public static void main(String[] args) {
        EmployeeView view = new EmployeeView();
        view.menuView();
    }
}
