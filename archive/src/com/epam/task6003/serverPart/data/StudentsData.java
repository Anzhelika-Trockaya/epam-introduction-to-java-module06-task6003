package com.epam.task6003.serverPart.data;

import com.epam.task6003.serverPart.model.Student;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlType(name = "studentsData")
@XmlRootElement
public class StudentsData {
    private ArrayList<Student> students;

    public StudentsData() {
    }

    public StudentsData(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

}
