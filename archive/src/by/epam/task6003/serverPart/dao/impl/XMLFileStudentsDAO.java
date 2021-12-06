package by.epam.task6003.serverPart.dao.impl;

import by.epam.task6003.serverPart.bean.Student;
import by.epam.task6003.serverPart.dao.DAOException;
import by.epam.task6003.serverPart.dao.StudentsDAO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XMLFileStudentsDAO implements StudentsDAO {
    private final String fileName;
    private List<Student> students;

    public XMLFileStudentsDAO() {
        fileName = getClass()
                .getResource("/by/epam/task6003/serverPart/resource/studentsData.xml")
                .toString()
                .substring(6);

        students = null;
    }

    private void loadStudents() throws DAOException {
        JAXBContext context;
        Unmarshaller unmarshaller;
        StudentsData studentsData;

        try (FileInputStream reader = new FileInputStream(fileName)) {

            context = JAXBContext.newInstance(StudentsData.class);
            unmarshaller = context.createUnmarshaller();
            studentsData = (StudentsData) unmarshaller.unmarshal(reader);

            students = studentsData.getStudents();

        } catch (IOException | JAXBException exception) {
            throw new DAOException(exception);
        }

    }

    private void unloadStudents() throws DAOException {
        StudentsData studentsData;
        JAXBContext context;
        Marshaller marshaller;

        try (FileOutputStream output = new FileOutputStream(fileName)) {

            studentsData = new StudentsData(students);
            context = JAXBContext.newInstance(StudentsData.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(studentsData, output);

        } catch (IOException | JAXBException exception) {
            throw new DAOException(exception);
        }
    }


    @Override
    public List<Student> getAllStudents() throws DAOException {
        if (students == null) {
            loadStudents();
        }

        return students;
    }

    @Override
    public Student getStudent(int id) throws DAOException {
        if (students == null) {
            loadStudents();
        }

        for (Student student : students) {
            if (id == student.getId()) {
                return student;
            }
        }

        return null;
    }

    @Override
    public void addStudent(Student student) throws DAOException {
        if (students == null) {
            loadStudents();
        }

        students.add(student);

        unloadStudents();
    }

    @Override
    public void removeStudent(int id) throws DAOException {
        if (students == null) {
            loadStudents();
        }

        students.removeIf(student -> id == student.getId());

        unloadStudents();
    }

    @Override
    public void changeStudent(Student student) throws DAOException {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                unloadStudents();
            }
        }
    }
}
