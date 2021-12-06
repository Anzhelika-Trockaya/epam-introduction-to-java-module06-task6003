package by.epam.task6003.serverPart.controller.impl;

import by.epam.task6003.serverPart.bean.User;
import by.epam.task6003.serverPart.controller.Controller;
import by.epam.task6003.serverPart.controller.impl.command.Command;
import by.epam.task6003.serverPart.controller.impl.command.CommandProvider;
import by.epam.task6003.serverPart.service.ServiceException;
import by.epam.task6003.serverPart.service.ServiceProvider;
import by.epam.task6003.serverPart.service.UsersService;
import by.epam.task6003.serverPart.view.UserActionView;
import by.epam.task6003.serverPart.view.ViewProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArchiveController implements Controller {
    private final ServiceProvider serviceProvider;
    private final ViewProvider viewProvider;
    private final CommandProvider commandProvider;
    private final Map<String, User> users;

    public ArchiveController() {
        serviceProvider = ServiceProvider.getInstance();
        viewProvider = ViewProvider.getInstance();
        commandProvider = CommandProvider.getInstance();
        users = new HashMap<>();
    }

    public String doAction(String request) {
        String commandName;
        String usersKey;
        User currentUser;
        Command currentCommand;
        List<String> params;
        String response;
        UserActionView userActionView;

        userActionView = viewProvider.getUserActionView();

        if (isRequestCorrect(request)) {

            commandName = takeCommandName(request);
            params = takeParamsList(request);

            if (!commandName.equals("AUTHORIZATION")) {

                usersKey = takeUsersKey(request);

                if (users.containsKey(usersKey)) {

                    currentUser = users.get(usersKey);
                    currentCommand = commandProvider.getCommand(commandName, currentUser);
                    response = currentCommand.execute(params);

                } else {
                    response = userActionView.getExceptionResponse("User is not authorized!");
                }

            } else {
                response = authorization(params);
            }

        } else {
            response = userActionView.getExceptionResponse("Incorrect request: " + request);
        }

        return response;
    }

    private boolean isRequestCorrect(String request) {
        String requestRegex;

        requestRegex = "([_A-Z]+\\skey=\"\\d+\"((\\s[a-zA-Z]+=\".+\")|(\n(\n|.)+))*)|(AUTHORIZATION\\slogin=\".+\"\\spassword=\".+\")";

        return request.startsWith("CHANGE_STUDENT key=\"") || request.startsWith("ADD_STUDENT key=\"") || request.matches(requestRegex);
    }

    private String generateKey(User currentUser) {
        int key;

        key = currentUser.getLogin().hashCode();

        return Integer.toString(key);
    }

    private String authorization(List<String> params) {
        String login;
        String password;
        String key;
        User currentUser;
        String response;

        UsersService usersService;
        UserActionView userActionView;

        usersService = serviceProvider.getUsersService();
        userActionView = viewProvider.getUserActionView();

        login = params.get(0).substring(7, params.get(0).length() - 1);
        password = params.get(1).substring(10, params.get(1).length() - 1);
        key = null;

        try {
            currentUser = usersService.authorization(login, password);

            if (currentUser != null) {
                key = generateKey(currentUser);
                users.put(key, currentUser);
            }

            response = userActionView.getAuthorizationResponse(currentUser, key);

        } catch (ServiceException serviceException) {
            response = userActionView.getExceptionResponse(serviceException);
        }

        return response;

    }

    private String takeUsersKey(String request) {
        boolean isStartedKey;
        char mark;
        char currentChar;
        StringBuilder keyBuilder;

        mark = '\"';
        isStartedKey = false;
        keyBuilder = new StringBuilder();

        for (int i = 0; i < request.length(); i++) {

            currentChar = request.charAt(i);

            if (mark == currentChar && !isStartedKey) {

                isStartedKey = true;

            } else if (isStartedKey) {

                if (mark != currentChar) {
                    keyBuilder.append(currentChar);
                } else {
                    return keyBuilder.toString();
                }

            }
        }

        return null;
    }

    private String takeCommandName(String request) {
        StringBuilder commandNameBuilder;

        commandNameBuilder = new StringBuilder();

        int i = 0;

        while (i < request.length() && request.charAt(i) != ' ') {
            commandNameBuilder.append(request.charAt(i));
            i++;

        }

        return commandNameBuilder.toString();
    }

    private List<String> takeParamsList(String request) {
        StringBuilder parameterBuilder;
        List<String> paramsList;
        char currentChar;
        boolean isQuotationMarkOpen;

        paramsList = new ArrayList<>();
        parameterBuilder = new StringBuilder();

        int i = foundStartIndexOfParams(request);

        isQuotationMarkOpen = false;


        for (; i < request.length(); i++) {

            currentChar = request.charAt(i);

            if (currentChar != '\n') {

                if (currentChar == '"') {
                    isQuotationMarkOpen = !isQuotationMarkOpen;
                }

                if (currentChar != ' ' || isQuotationMarkOpen) {

                    parameterBuilder.append(currentChar);

                } else {

                    paramsList.add(parameterBuilder.toString());
                    parameterBuilder = new StringBuilder();

                }

            } else {
                break;
            }

        }

        if (!parameterBuilder.isEmpty()) {
            paramsList.add(parameterBuilder.toString());
        }

        if (i < request.length()) {
            paramsList.add(request.substring(i + 1));
        }

        deleteKeyFromParams(paramsList);

        return paramsList;
    }

    private List<String> deleteKeyFromParams(List<String> params) {
        if (params.get(0).startsWith("key=")) {
            params.remove(0);
        }

        return params;
    }

    private int foundStartIndexOfParams(String request) {
        int i = 1;

        while (i < request.length() && request.charAt(i - 1) != ' ') {
            i++;
        }

        return i;
    }

}
