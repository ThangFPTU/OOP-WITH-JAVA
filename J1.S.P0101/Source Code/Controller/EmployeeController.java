/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Employee;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author hoang
 */
public class EmployeeController {

    private ArrayList<Employee> listEmployees = new ArrayList<>();

    public EmployeeController() {
        listEmployees.add(new Employee(1, "A", "Nguyen Van", "0123456789",
                "123@gamail.com", "Ha Noi", "21/07/2000", true, 900, "None"));
        listEmployees.add(new Employee(2, "B", "Nguyen Van", "9876543210",
                "131@gamail.com", "Ha Noi", "22/08/2000", false, 1000, "None"));
    }

    public ArrayList<Employee> getListEmployees() {
        return listEmployees;
    }

    public boolean addEmployee(int id, String firstName, String lastName, String phone, String email, String address, String dob, boolean sex, double salary, String agency) {
        return listEmployees.add(new Employee(id, firstName, lastName, phone, email, address, dob, sex, salary, agency));
    }

    public void updateEmployee(int id, String firstName, String lastName, String phone, String email, String address, String dob, Boolean sex, double salary, String agency) {

        Employee em = getEployeeById(id);
        if (firstName != "") {
            em.setFirstName(firstName);
        }
        if (lastName != "") {
            em.setLastName(lastName);
        }
        if (address != "") {
            em.setAddress(address);
        }
        if (agency != "") {
            em.setAgency(agency);
        }
        if (email != "") {
            em.setEmail(email);
        }
        if (phone != "") {
            em.setPhone(phone);
        }
        if (dob != "") {
            em.setDob(dob);
        }
        if (salary != 0) {
            em.setSalary(salary);
        }
        if (sex != null) {
            em.setSex(sex);
        }

    }

    public Employee getEployeeById(int id) {
        for (Employee em : listEmployees) {
            if (em.getId() == id) {
                return em;
            }
        }
        return null;
    }

    public boolean removeEmployee(int id) {
        for (Employee em : listEmployees) {
            if (em.getId() == id) {
                listEmployees.remove(em);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Employee> searchEmployeeByName(String searchValue) {
        if (searchValue == "") {
            return listEmployees;
        } else {
            ArrayList<Employee> ListSearch = new ArrayList<>();
            for (Employee em : listEmployees) {
                String name = em.getFirstName() + em.getLastName();
                if (name.toUpperCase().contains(searchValue.toUpperCase())) {
                    ListSearch.add(em);
                }
            }
            return ListSearch;
        }
    }

    public ArrayList<Employee> sortEmployeeBySalary() {
        ArrayList<Employee> sortedEmployee = listEmployees;
        Collections.sort(sortedEmployee);
        return sortedEmployee;
    }
}
