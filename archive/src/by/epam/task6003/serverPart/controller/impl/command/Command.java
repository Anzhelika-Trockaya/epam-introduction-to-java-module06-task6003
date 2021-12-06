package by.epam.task6003.serverPart.controller.impl.command;

import java.util.List;

public interface Command {
    String execute(List<String> params);
}
