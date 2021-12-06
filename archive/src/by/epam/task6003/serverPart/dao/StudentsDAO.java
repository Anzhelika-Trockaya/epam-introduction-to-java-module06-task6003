package by.epam.task6003.serverPart.dao;

import by.epam.task6003.serverPart.bean.Student;

import java.util.List;

public interface StudentsDAO {
    List<Student> getAllStudents() throws DAOException;
    Student getStudent(int id) throws DAOException;
    void addStudent(Student student) throws DAOException;
    void removeStudent(int id) throws DAOException;
    void changeStudent(Student student) throws DAOException;
}
