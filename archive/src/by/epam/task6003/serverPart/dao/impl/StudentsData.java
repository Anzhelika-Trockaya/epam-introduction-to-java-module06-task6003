package by.epam.task6003.serverPart.dao.impl;

import by.epam.task6003.serverPart.bean.Student;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "studentsData")
@XmlRootElement
public class StudentsData {
    private List<Student> students;

    public StudentsData() {
    }

    public StudentsData(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
