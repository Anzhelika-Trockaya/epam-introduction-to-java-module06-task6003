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

import javax.xml.bind.JAXBException;
import java.util.List;

public class ChangeStudentCommand implements Command {
    private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final ViewProvider viewProvider = ViewProvider.getInstance();

    @Override
    public String execute(List<String> params) {
        Student student;
        String response;
        StudentsService studentsService;
        UserActionView userActionView;
        StudentXMLConverter studentXMLConverter;

        studentsService = serviceProvider.getStudentsService();
        userActionView = viewProvider.getUserActionView();
        studentXMLConverter = StudentXMLConverter.getInstance();

        try {

            student = studentXMLConverter.unmarshalStudent(params.get(0));
            studentsService.changeStudent(student);

            response = userActionView.getStudentIsChangedResponse();

        } catch (ParamsException paramsException) {
            response = userActionView.getStudentIsNotChangedResponse(paramsException.getMessage());
        } catch (JAXBException | ServiceException exception) {
            response = userActionView.getExceptionResponse(exception);
        }

        return response;
    }

}
