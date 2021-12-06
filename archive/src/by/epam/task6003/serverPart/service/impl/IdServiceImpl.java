package by.epam.task6003.serverPart.service.impl;

import by.epam.task6003.serverPart.dao.DAOException;
import by.epam.task6003.serverPart.dao.DAOProvider;
import by.epam.task6003.serverPart.dao.IdDAO;
import by.epam.task6003.serverPart.service.IdService;
import by.epam.task6003.serverPart.service.ServiceException;

public class IdServiceImpl implements IdService {
    private DAOProvider daoProvider = DAOProvider.getInstance();

    @Override
    public int getNextId() throws ServiceException {
        IdDAO idDAO;
        int currentId;
        int nextId;

        try {

            idDAO = daoProvider.getIdDAO();

            currentId = idDAO.readCurrentId();
            nextId = currentId + 1;
            idDAO.writeCurrentId(nextId);

            return nextId;

        } catch (DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }
}
