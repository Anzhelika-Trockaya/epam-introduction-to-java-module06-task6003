package by.epam.task6003.serverPart.controller.impl.command.impl;

import by.epam.task6003.serverPart.controller.impl.command.Command;
import by.epam.task6003.serverPart.view.UserActionView;
import by.epam.task6003.serverPart.view.ViewProvider;

import java.util.List;

public class NoCommandCommand implements Command {
    private final ViewProvider viewProvider = ViewProvider.getInstance();
    private String commandName;

    public NoCommandCommand(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public String execute(List<String> params) {
        UserActionView userActionView;
        String response;

        userActionView = viewProvider.getUserActionView();
        response = userActionView.getIncorrectCommandNameResponse(commandName);

        return response;
    }
}
