package by.epam.task6003.serverPart.dao;

import by.epam.task6003.serverPart.dao.impl.XMLFileIdDAO;
import by.epam.task6003.serverPart.dao.impl.XMLFileStudentsDAO;
import by.epam.task6003.serverPart.dao.impl.XMLFileUsersDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private final IdDAO idDAO;
    private final StudentsDAO studentsDAO;
    private final UsersDAO usersDAO;

    private DAOProvider(){
        idDAO=new XMLFileIdDAO();
        studentsDAO=new XMLFileStudentsDAO();
        usersDAO=new XMLFileUsersDAO();
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public IdDAO getIdDAO() {
        return idDAO;
    }

    public StudentsDAO getStudentsDAO() {
        return studentsDAO;
    }

    public UsersDAO getUsersDAO(){
        return usersDAO;
    }
}
