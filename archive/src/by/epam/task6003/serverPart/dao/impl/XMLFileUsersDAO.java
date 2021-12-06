package by.epam.task6003.serverPart.dao.impl;

import by.epam.task6003.serverPart.bean.User;
import by.epam.task6003.serverPart.dao.DAOException;
import by.epam.task6003.serverPart.dao.UsersDAO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XMLFileUsersDAO implements UsersDAO {
    private final String fileName = getClass()
            .getResource("/by/epam/task6003/serverPart/resource/usersData.xml")
            .toString()
            .substring(6);

    @Override
    public List<User> loadUsers() throws DAOException {
        JAXBContext context;
        Unmarshaller unmarshaller;
        UsersData usersData;

        try(FileInputStream reader = new FileInputStream(fileName)) {

            context = JAXBContext.newInstance(UsersData.class);
            unmarshaller = context.createUnmarshaller();
            usersData = (UsersData) unmarshaller.unmarshal(reader);

            return usersData.getUsers();

        } catch(IOException | JAXBException exception){
            throw new DAOException(exception);
        }
    }

    @Override
    public void unloadUsers(List<User> usersList) throws DAOException {
        UsersData usersData;
        JAXBContext context;
        Marshaller marshaller;

        try(FileOutputStream output = new FileOutputStream(fileName)) {

            usersData = new UsersData(usersList);
            context = JAXBContext.newInstance(UsersData.class);
            marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(usersData, output);

        } catch(IOException|JAXBException exception){
            throw new DAOException(exception);
        }
    }
}
