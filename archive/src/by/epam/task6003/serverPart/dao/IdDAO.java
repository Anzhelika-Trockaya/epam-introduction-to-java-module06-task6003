package by.epam.task6003.serverPart.dao;

public interface IdDAO {
    int readCurrentId() throws DAOException;
    void writeCurrentId(int id) throws DAOException;
}
