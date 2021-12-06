package by.epam.task6003.clientPart.controller.impl;

import by.epam.task6003.clientPart.controller.Controller;

import java.io.IOException;

public class ArchiveClientController implements Controller {
    private final Connection connection;

    public ArchiveClientController() throws IOException {
        connection = new Connection();
    }

    @Override
    public String doAction(String request) {
        String response;

        try {
            connection.sendRequest(request);

            if (!"EXIT".equals(request)) {
                response = connection.getResponse();
            } else {
                response = null;
                exit();
            }

        } catch (IOException ioException) {
            response = "-1 SERVER ERROR! Try later!";
        }

        return response;
    }

    private void exit() {
        try {
            connection.close();
        } catch (IOException ioException) {
            System.exit(-1);
        } finally {
            System.exit(1);
        }
    }
}
