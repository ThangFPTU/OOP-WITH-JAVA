/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hoang
 */
public class Student implements Comparable<Student> {

    private String id;
    private String name;
    private int semester;
    private int course;

    public Student() {
    }

    public Student(String id, String name, int semester, int courseName) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.course = courseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        switch (course) {
            case 1:
                return "Java";
            case 2:
                return ".Net";
            case 3:
                return "C/C++";
        }
        return null;
    }

    public int getCourse() {
        return course;

    }

    public void setCourse(int course) {
        this.course = course;
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.getName());
    }

}
