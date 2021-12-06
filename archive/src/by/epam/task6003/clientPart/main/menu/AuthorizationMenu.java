package by.epam.task6003.clientPart.main.menu;

import by.epam.task6003.clientPart.controller.Controller;
import by.epam.task6003.clientPart.main.UserInput;
import by.epam.task6003.clientPart.view.UserActionView;
import by.epam.task6003.clientPart.view.ViewProvider;


public class AuthorizationMenu {
    private final UserInput userInput;
    private final ViewProvider viewProvider;
    private final Controller controller;

    public AuthorizationMenu(Controller controller) {
        this.controller = controller;
        userInput = new UserInput();
        viewProvider = ViewProvider.getInstance();
    }

    public String generateAuthorizationRequest() {
        String request;
        String login;
        String password;

        MenuShowerUtil.showTitle();

        login = userInput.readLine("Enter login: ");
        password = userInput.readLine("Enter password: ");

        request = "AUTHORIZATION login=\"" + login + "\" password=\"" + password + "\"";

        return request;
    }

    public Menu getMenu(String response) {
        String userRole;
        String key;
        Menu menu;
        UserActionView userActionView;
        String message;

        menu = null;
        userActionView = viewProvider.getUserActionView();

        if (response.startsWith("0 ") && isSuccessfulResponseCorrect(response)) {

            userRole = takeUserRole(response);
            key = takeKey(response);

            if ("ADMIN".equals(userRole)) {
                menu = new AdminMenu(key, controller);
            } else if ("USER".equals(userRole)) {
                menu = new Menu(key, controller);
            }

        }

        message = userActionView.getAuthorizationInfo(response);
        MenuShowerUtil.showMessage(message);

        if (response.startsWith("-1")) {
            System.exit(-1);
        }

        return menu;
    }

    private boolean isSuccessfulResponseCorrect(String response) {
        String responseRegex;

        responseRegex = "0 key=\"\\d+\" role=\"(ADMIN|USER)\"\n.+";

        return response.matches(responseRegex);
    }

    private String takeUserRole(String response) {
        String[] responseParts;
        String role;

        responseParts = response.split(" ");
        role = responseParts[2].substring(6, responseParts[2].length()-2);

        return role;
    }

    private String takeKey(String response) {
        String[] responseParts;
        String key;

        responseParts = response.split(" ");
        key = responseParts[1].substring(5, responseParts[1].length()-1);

        return key;
    }
}
