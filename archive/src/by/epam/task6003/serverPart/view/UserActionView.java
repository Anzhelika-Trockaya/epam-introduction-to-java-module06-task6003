package by.epam.task6003.serverPart.view;

import by.epam.task6003.serverPart.bean.Student;
import by.epam.task6003.serverPart.bean.User;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface UserActionView {

    String getAllStudentsInfoResponse(List<Student> students);

    String getExceptionResponse(Exception serviceException);

    String getExceptionResponse(String message);

    String getIncorrectParamsResponse(List<String> params);

    String getStudentResponse(Student student) throws JAXBException, IOException;

    String getAuthorizationResponse(User user, String key);

    String getRemoveStudentResponse();

    String getNoStudentResponse();

    String getIncorrectCommandNameResponse(String commandName);

    String getStudentIsChangedResponse();

    String getStudentIsNotChangedResponse(String message);

    String getStudentIsNotAddedResponse(String message);
}
