package by.epam.task6003.serverPart.service.impl;

import by.epam.task6003.serverPart.bean.Student;
import by.epam.task6003.serverPart.dao.DAOException;
import by.epam.task6003.serverPart.dao.DAOProvider;
import by.epam.task6003.serverPart.dao.StudentsDAO;
import by.epam.task6003.serverPart.service.IdService;
import by.epam.task6003.serverPart.service.ServiceException;
import by.epam.task6003.serverPart.service.ServiceProvider;
import by.epam.task6003.serverPart.service.StudentsService;

import java.util.List;

public class StudentsServiceImpl implements StudentsService {
    private final DAOProvider daoProvider = DAOProvider.getInstance();

    @Override
    public List<Student> getAllStudents() throws ServiceException {
        StudentsDAO studentsDAO;
        List<Student> allStudent;

        try {
            studentsDAO = daoProvider.getStudentsDAO();
            allStudent = studentsDAO.getAllStudents();

            return allStudent;
        } catch (DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public Student getStudent(int id) throws ServiceException {
        StudentsDAO studentsDAO;
        Student student;

        try {
            studentsDAO = daoProvider.getStudentsDAO();
            student = studentsDAO.getStudent(id);

            return student;
        } catch (DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public Student addAndGetStudent(Student student) throws ServiceException, ParamsException {
        StudentsDAO studentsDAO;
        ServiceProvider serviceProvider;
        IdService idService;

        try {
            studentsDAO = daoProvider.getStudentsDAO();
            serviceProvider = ServiceProvider.getInstance();
            idService = serviceProvider.getIdService();

            checkStudentsParams(student);
            student.setId(idService.getNextId());
            studentsDAO.addStudent(student);

            return student;

        } catch (DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    private void checkStudentsParams(Student student) throws ParamsException {
        StudentsParamsChecker checker;

        checker = StudentsParamsChecker.getInstance();

        checker.checkParams(student);
    }

    @Override
    public void removeStudent(int id) throws ServiceException {
        StudentsDAO studentsDAO;

        try {
            studentsDAO = daoProvider.getStudentsDAO();
            studentsDAO.removeStudent(id);

        } catch (DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void changeStudent(Student student) throws ServiceException, ParamsException {
        StudentsDAO studentsDAO;

        try {
            studentsDAO = daoProvider.getStudentsDAO();

            checkStudentsParams(student);
            studentsDAO.changeStudent(student);

        } catch (DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }
}
