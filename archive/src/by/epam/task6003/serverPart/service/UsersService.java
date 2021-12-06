package by.epam.task6003.serverPart.service;

import by.epam.task6003.serverPart.bean.User;

public interface UsersService {
    User authorization(String login, String password) throws ServiceException;

}
