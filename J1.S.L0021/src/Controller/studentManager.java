/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author hoang
 */
public class studentManager {

    public void createStudent(ArrayList<Student> listSt, String id, String name,
            int semester, int course) {
        listSt.add(new Student(id, name, semester, course));
    }

    public void deleteStudent(ArrayList<Student> listSt,String id) {
        for (Student st : listSt) {
            if(st.getId().equals(id)){
                listSt.remove(st);
            }
        }
    }
    
    public ArrayList<Student> getStudentById(ArrayList<Student> listSt,String id){
        ArrayList<Student> listSearch = new ArrayList<>();
        for (Student st : listSt) {
            if(st.getId().equals(id)){
                listSearch.add(st);
            }
        }
        return  listSearch;
    }

    public void updateStudent(ArrayList<Student> listSt,String id) {
        
    }

    public HashMap<Student, Integer> getReport() {
        return null;
    }

    public ArrayList<Student> searchStudents(ArrayList<Student> listSt, String searchValue) {
        ArrayList<Student> listSearch = new ArrayList<>();
        for (Student st : listSt) {
            String stName = st.getName().toUpperCase();
            if(stName.contains(searchValue.toUpperCase())){
                listSearch.add(st);
            }
        }
        return listSearch;
    }

    public void sortByName(ArrayList<Student> listSearch) {
        Collections.sort(listSearch);
    }
}
