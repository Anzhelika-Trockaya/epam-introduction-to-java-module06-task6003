package by.epam.task6003.serverPart.controller.impl.command.impl;

import by.epam.task6003.serverPart.bean.Student;
import by.epam.task6003.serverPart.controller.impl.command.Command;
import by.epam.task6003.serverPart.service.ServiceException;
import by.epam.task6003.serverPart.service.ServiceProvider;
import by.epam.task6003.serverPart.service.StudentsService;
import by.epam.task6003.serverPart.view.UserActionView;
import by.epam.task6003.serverPart.view.ViewProvider;

import java.util.List;

public class GetStudentsInfoListCommand implements Command {
    private final ServiceProvider serviceProvider=ServiceProvider.getInstance();
    private final ViewProvider viewProvider = ViewProvider.getInstance();

    @Override
    public String execute(List<String> params) {
        StudentsService studentsService;
        UserActionView userActionView;
        List<Student> students;
        String response;

        studentsService = serviceProvider.getStudentsService();
        userActionView=viewProvider.getUserActionView();

        try {
            students = studentsService.getAllStudents();
            response=userActionView.getAllStudentsInfoResponse(students);

        } catch(ServiceException serviceException){
            response=userActionView.getExceptionResponse(serviceException);
        }

        return response;
    }

}
