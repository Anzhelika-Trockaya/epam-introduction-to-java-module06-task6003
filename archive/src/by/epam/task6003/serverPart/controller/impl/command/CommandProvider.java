package by.epam.task6003.serverPart.controller.impl.command;

import by.epam.task6003.serverPart.bean.User;
import by.epam.task6003.serverPart.bean.UserRole;
import by.epam.task6003.serverPart.controller.impl.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final Map<String, Command> usersCommands;
    private final Map<String, Command> adminsCommands;

    private CommandProvider() {
        usersCommands = new HashMap<>();
        adminsCommands = new HashMap<>();

        usersCommands.put("GET_STUDENTS_INFO", new GetStudentsInfoListCommand());
        usersCommands.put("GET_STUDENT", new GetStudentCommand());

        adminsCommands.putAll(usersCommands);
        adminsCommands.put("CHANGE_STUDENT", new ChangeStudentCommand());
        adminsCommands.put("ADD_STUDENT", new AddStudentCommand());
        adminsCommands.put("REMOVE_STUDENT", new RemoveStudentCommand());

    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName, User user) {
        Command command;

        if (UserRole.ADMIN == user.getRole()) {
            command = adminsCommands.get(commandName);
        } else {
            command = usersCommands.get(commandName);
        }

        if (command == null) {
            command = new NoCommandCommand(commandName);
        }

        return command;
    }
}
