package by.epam.task6003.serverPart.controller.impl.command.impl;

import by.epam.task6003.serverPart.bean.Student;
import by.epam.task6003.serverPart.controller.impl.command.Command;
import by.epam.task6003.serverPart.service.ServiceException;
import by.epam.task6003.serverPart.service.ServiceProvider;
import by.epam.task6003.serverPart.service.StudentsService;
import by.epam.task6003.serverPart.view.UserActionView;
import by.epam.task6003.serverPart.view.ViewProvider;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class GetStudentCommand implements Command {
    private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final ViewProvider viewProvider = ViewProvider.getInstance();

    @Override
    public String execute(List<String> params) {
        StudentsService studentsService;
        UserActionView userActionView;
        int id;
        Student student;
        String response;

        studentsService = serviceProvider.getStudentsService();
        userActionView = viewProvider.getUserActionView();


        if (areParamsCorrect(params)) {

            id = takeId(params.get(0));

            try {

                student = studentsService.getStudent(id);

                if(student!=null) {
                    response = userActionView.getStudentResponse(student);
                } else{
                    response=userActionView.getNoStudentResponse();
                }

            } catch (ServiceException | JAXBException | IOException exception) {
                response = userActionView.getExceptionResponse(exception);
            }

        } else {
            response = userActionView.getIncorrectParamsResponse(params);
        }

        return response;
    }

    private boolean areParamsCorrect(List<String> params) {
        String idRegex;

        idRegex = "id=\"[0-9]+\"";

        return params.size() == 1 && params.get(0).matches(idRegex);
    }

    private int takeId(String param) {
        int id;

        id = Integer.parseInt(param.substring(4, param.length()-1));

        return id;
    }
}
