package by.epam.task6003.serverPart.main;

import by.epam.task6003.serverPart.controller.impl.ArchiveController;
import by.epam.task6003.serverPart.controller.Controller;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        String request;
        String response;
        Controller archiveController;
        ClientConnection clientConnection;

        archiveController = new ArchiveController();

        try {

            clientConnection = new ClientConnection();

                while (true) {

                    try {
                        clientConnection.accept();

                        request = clientConnection.getRequest();

                        while (!request.equals("EXIT")) {
                            response = archiveController.doAction(request);
                            clientConnection.sendResponse(response);
                            request = clientConnection.getRequest();
                        }

                        clientConnection.close();
                    } catch(IOException ioException){
                    }
                }

        } catch (IOException ioException) {
            System.out.println("ERROR!");
            System.exit(-1);
        }

    }
}
