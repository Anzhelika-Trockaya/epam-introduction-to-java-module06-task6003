package by.epam.task6003.clientPart.main;


import by.epam.task6003.clientPart.controller.Controller;
import by.epam.task6003.clientPart.controller.impl.ArchiveClientController;
import by.epam.task6003.clientPart.main.menu.AuthorizationMenu;
import by.epam.task6003.clientPart.main.menu.Menu;
import by.epam.task6003.clientPart.main.menu.MenuException;
import by.epam.task6003.clientPart.main.menu.MenuShowerUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) {
        String request;
        String response;
        Controller controller;
        AuthorizationMenu authorizationMenu;
        Menu menu;

        try {
            controller = new ArchiveClientController();
            authorizationMenu = new AuthorizationMenu(controller);
            menu = null;

            while (menu == null) {
                request = authorizationMenu.generateAuthorizationRequest();
                response = controller.doAction(request);
                menu = authorizationMenu.getMenu(response);
            }

            while (true) {
                menu.processMainMenu();
            }

        } catch (IOException | JAXBException | MenuException exception) {
            MenuShowerUtil.showMessage("ERROR!");
            System.exit(-1);
        }

    }
}
