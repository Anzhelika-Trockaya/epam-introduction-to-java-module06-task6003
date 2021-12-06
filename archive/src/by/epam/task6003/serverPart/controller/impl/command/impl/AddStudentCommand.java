package by.epam.task6003.serverPart.controller.impl.command.impl;

import by.epam.task6003.serverPart.bean.Student;
import by.epam.task6003.serverPart.controller.impl.command.Command;
import by.epam.task6003.serverPart.service.ServiceException;
import by.epam.task6003.serverPart.service.ServiceProvider;
import by.epam.task6003.serverPart.service.StudentsService;
import by.epam.task6003.serverPart.service.impl.ParamsException;
import by.epam.task6003.serverPart.view.StudentXMLConverter;
import by.epam.task6003.serverPart.view.UserActionView;
import by.epam.task6003.serverPart.view.ViewProvider;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class AddStudentCommand implements Command {
    private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final ViewProvider viewProvider = ViewProvider.getInstance();

    @Override
    public String execute(List<String> params) {
        Student student;
        String response;
        StudentsService studentsService;
        UserActionView userActionView;
        StudentXMLConverter studentXMLConverter;

        studentXMLConverter=StudentXMLConverter.getInstance();
        studentsService = serviceProvider.getStudentsService();
        userActionView = viewProvider.getUserActionView();

        try {

            student = studentXMLConverter.unmarshalStudent(params.get(0));
            student = studentsService.addAndGetStudent(student);
            response = userActionView.getStudentResponse(student);

        } catch (ParamsException paramsException) {
            response=userActionView.getStudentIsNotAddedResponse(paramsException.getMessage());
        }catch (JAXBException | IOException | ServiceException exception) {
            response = userActionView.getExceptionResponse(exception);
        }

        return response;
    }
}
