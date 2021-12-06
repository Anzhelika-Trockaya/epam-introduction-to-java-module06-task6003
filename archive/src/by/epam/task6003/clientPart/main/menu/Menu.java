package by.epam.task6003.clientPart.main.menu;


import by.epam.task6003.clientPart.bean.Student;
import by.epam.task6003.clientPart.controller.Controller;
import by.epam.task6003.clientPart.main.UserInput;
import by.epam.task6003.clientPart.view.UserActionView;
import by.epam.task6003.clientPart.view.ViewProvider;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Menu {
    private final String key;
    private final UserInput userInput;
    private final Controller controller;
    private final ViewProvider viewProvider;

    public Menu(String key, Controller controller) {
        this.key = key;
        this.controller = controller;
        userInput = new UserInput();
        viewProvider = ViewProvider.getInstance();
    }

    public String getKey() {
        return key;
    }

    public UserInput getUserInput() {
        return userInput;
    }

    public Controller getController() {
        return controller;
    }

    public ViewProvider getViewProvider() {
        return viewProvider;
    }

    public void processMainMenu() throws JAXBException, IOException, MenuException {
        String input;

        MenuShowerUtil.showUserStartMenu();
        input = getUserInput().readLine("Enter here: ");

        switch (input) {
            case "1" -> processViewStudents();
            case "0" -> exit();
        }
    }

    protected String getStudentsInfoResponse() {
        String request;
        String response;

        request = "GET_STUDENTS_INFO key=\"" + key + "\"";
        response = controller.doAction(request);

        return response;
    }

    public void processViewStudents() throws JAXBException, IOException, MenuException {
        String message;
        String enterMessage;
        String response;
        String input;
        String inputRegex;
        UserActionView userActionView;

        inputRegex="\\d+";
        enterMessage="Enter student ID or \"0\" to return: ";
        userActionView = viewProvider.getUserActionView();

        MenuShowerUtil.showViewTitle();
        response=getStudentsInfoResponse();
        message=userActionView.getInfo(response);
        MenuShowerUtil.showMessage(message);

        input=getUserInput().readLine(enterMessage);

        while(!"0".equals(input)){

            if(response.startsWith("0")) {

                while(!input.matches(inputRegex)) {
                    MenuShowerUtil.showMessage("Incorrect id: " + input);
                    input=getUserInput().readLine(enterMessage);
                }

                if (!"0".equals(input)) {
                    processViewStudent(input);

                    MenuShowerUtil.showViewTitle();
                    response=getStudentsInfoResponse();
                    message=userActionView.getInfo(response);
                    MenuShowerUtil.showMessage(message);
                    input=getUserInput().readLine(enterMessage);
                }

            } else{
                processBackMenu();
            }
        }
    }

    public void processViewStudent(String id) throws JAXBException, MenuException, IOException {
        Student student;
        String message;
        String response;
        UserActionView userActionView;
        StudentXMLConverter studentXMLConverter;

        userActionView = viewProvider.getUserActionView();
        studentXMLConverter = StudentXMLConverter.getInstance();

        response = getStudentCaseResponse(id);

        if (response.startsWith("0\n")) {

            student = studentXMLConverter.unmarshalStudent(response.substring(2));
            message = userActionView.getStudentInfo(student);

        } else {

            message = userActionView.getInfo(response);

        }

        MenuShowerUtil.showMessage(message);
        processBackMenu();

    }

    public String getStudentCaseResponse(String id) {
        String request;
        String response;

        request = "GET_STUDENT key=\"" + key + "\" id=\"" + id + "\"";
        response = controller.doAction(request);

        return response;
    }

    public void processBackMenu() {
        String input;
        String message;

        message = "Enter 0 to return: ";
        input = userInput.readLine(message);

        while (!"0".equals(input)) {
            input = userInput.readLine(message);
        }
    }

    public void exit() {
        String request;

        request = "EXIT";
        controller.doAction(request);
        System.exit(0);
    }
}
