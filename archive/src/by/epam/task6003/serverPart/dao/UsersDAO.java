package by.epam.task6003.serverPart.dao;

import by.epam.task6003.serverPart.bean.User;

import java.util.List;

public interface UsersDAO {
    List<User> loadUsers() throws DAOException;
    void unloadUsers(List<User> usersList) throws DAOException;
}
