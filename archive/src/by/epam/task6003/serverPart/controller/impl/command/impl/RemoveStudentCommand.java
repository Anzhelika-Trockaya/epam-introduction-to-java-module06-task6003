package by.epam.task6003.serverPart.controller.impl.command.impl;

import by.epam.task6003.serverPart.controller.impl.command.Command;
import by.epam.task6003.serverPart.service.ServiceException;
import by.epam.task6003.serverPart.service.ServiceProvider;
import by.epam.task6003.serverPart.service.StudentsService;
import by.epam.task6003.serverPart.view.UserActionView;
import by.epam.task6003.serverPart.view.ViewProvider;

import java.util.List;

public class RemoveStudentCommand implements Command {
    private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final ViewProvider viewProvider = ViewProvider.getInstance();

    @Override
    public String execute(List<String> params) {
        StudentsService studentsService;
        UserActionView userActionView;
        int id;
        String response;

        studentsService = serviceProvider.getStudentsService();
        userActionView = viewProvider.getUserActionView();

        if (areParamsCorrect(params)) {

            id = takeId(params.get(0));

            try {

                studentsService.removeStudent(id);
                response = userActionView.getRemoveStudentResponse();

            } catch (ServiceException serviceException) {
                response = userActionView.getExceptionResponse(serviceException);
            }

        } else {
            response = userActionView.getIncorrectParamsResponse(params);
        }

        return response;
    }

    private boolean areParamsCorrect(List<String> params) {
        String idRegex;

        idRegex = "id=\"[1-9][0-9]*\"";

        return params.size() == 1 && params.get(0).matches(idRegex);
    }

    private int takeId(String param) {
        int id;

        id = Integer.parseInt(param.substring(4, param.length()-1));

        return id;
    }
}
