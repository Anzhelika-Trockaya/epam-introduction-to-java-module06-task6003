package by.epam.task6003.serverPart.service;

import by.epam.task6003.serverPart.bean.Student;
import by.epam.task6003.serverPart.service.impl.ParamsException;

import java.util.List;

public interface StudentsService {

    List<Student> getAllStudents() throws ServiceException;

    Student getStudent(int id) throws ServiceException;

    Student addAndGetStudent(Student student) throws ServiceException, ParamsException;

    void removeStudent(int id) throws ServiceException;

    void changeStudent(Student student) throws ServiceException, ParamsException;
}
