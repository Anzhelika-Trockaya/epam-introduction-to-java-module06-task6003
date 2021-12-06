package by.epam.task6003.serverPart.service.impl;

import by.epam.task6003.serverPart.bean.User;
import by.epam.task6003.serverPart.dao.DAOException;
import by.epam.task6003.serverPart.dao.DAOProvider;
import by.epam.task6003.serverPart.dao.UsersDAO;
import by.epam.task6003.serverPart.service.ServiceException;
import by.epam.task6003.serverPart.service.UsersService;

import java.util.List;

public class UsersServiceImpl implements UsersService {
    private final DAOProvider daoProvider = DAOProvider.getInstance();

    @Override
    public User authorization(String login, String password) throws ServiceException {
        UsersDAO usersDAO;
        List<User> users;
        PasswordEncryptor passwordEncryptor;
        String decryptedPassword;

        try {
            usersDAO = daoProvider.getUsersDAO();
            passwordEncryptor = new PasswordEncryptor();
            users = usersDAO.loadUsers();

            for (User user : users) {

                decryptedPassword = passwordEncryptor.decryptPassword(user.getPassword());

                if (login.equals(user.getLogin()) && password.equals(decryptedPassword)) {
                    return user;
                }

            }

            return null;
        } catch (DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }
}
