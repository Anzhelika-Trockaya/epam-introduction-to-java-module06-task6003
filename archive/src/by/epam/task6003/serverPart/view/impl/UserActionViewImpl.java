package by.epam.task6003.serverPart.view.impl;

import by.epam.task6003.serverPart.bean.Student;
import by.epam.task6003.serverPart.bean.User;
import by.epam.task6003.serverPart.view.StudentXMLConverter;
import by.epam.task6003.serverPart.view.UserActionView;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class UserActionViewImpl implements UserActionView {
    @Override
    public String getAllStudentsInfoResponse(List<Student> students) {
        StringBuilder responseBuilder;
        String lineFormat;
        String titleLine;
        String currentStudentLine;
        String currentStudentFullName;

        responseBuilder = new StringBuilder("0 All students info");
        lineFormat = "\n%10s %35s %15s %8s %7s";
        titleLine = String.format(lineFormat, "id", "full name", "birthday date", "course", "group");

        responseBuilder.append(titleLine).append("\n");

        for (Student student : students) {

            currentStudentFullName = student.getSurname() + " " + student.getName() + " " + student.getPatronymic();

            currentStudentLine = String.format(lineFormat
                    , student.getId()
                    , currentStudentFullName
                    , student.takeBirthdayDate()
                    , student.getCourse()
                    , student.getGroup());

            responseBuilder.append(currentStudentLine);

        }

        return responseBuilder.toString();
    }

    @Override
    public String getExceptionResponse(Exception serviceException) {
        return "-1 " + serviceException.getMessage();
    }

    @Override
    public String getExceptionResponse(String message) {
        return "-1 " + message;
    }

    @Override
    public String getIncorrectParamsResponse(List<String> params) {
        return "-1 Incorrect params: " + params.toString();
    }

    @Override
    public String getStudentResponse(Student student) throws JAXBException, IOException {
        String studentXML;
        StudentXMLConverter studentXMLConverter;

        studentXMLConverter = StudentXMLConverter.getInstance();
        studentXML = studentXMLConverter.marshalStudent(student);

        return "0\n" + studentXML;
    }

    @Override
    public String getAuthorizationResponse(User user, String key) {
        String response;

        if (user != null) {
            response = "0 key=\"" + key + "\" role=\"" + user.getRole() + "\"\n Welcome " + user.getLogin() + "!";
        } else {
            response = "1 Incorrect login or password!";
        }

        return response;
    }

    @Override
    public String getRemoveStudentResponse() {
        return "0 Student is removed successfully!";
    }

    @Override
    public String getNoStudentResponse() {
        return "1 Student is not exist!";
    }

    @Override
    public String getIncorrectCommandNameResponse(String commandName) {
        return "-1 Incorrect command name: \"" + commandName + "\"";
    }

    @Override
    public String getStudentIsChangedResponse() {
        return "0 Student is changed successfully!";
    }

    @Override
    public String getStudentIsNotChangedResponse(String message) {
        return "1 " + message;
    }

    @Override
    public String getStudentIsNotAddedResponse(String message) {
        return "1 " + message;
    }

}
