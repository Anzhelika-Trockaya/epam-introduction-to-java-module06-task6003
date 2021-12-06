package by.epam.task6003.serverPart.dao.impl;

import by.epam.task6003.serverPart.dao.DAOException;
import by.epam.task6003.serverPart.dao.IdDAO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLFileIdDAO implements IdDAO {
    private final String currentIdFileName=getClass()
            .getResource("/by/epam/task6003/serverPart/resource/idData.xml")
            .toString()
            .substring(6);

    @Override
    public int readCurrentId() throws DAOException {
        JAXBContext context;
        Unmarshaller unmarshaller;
        IdData idData;


        try(FileInputStream reader = new FileInputStream(currentIdFileName)) {

            context = JAXBContext.newInstance(IdData.class);
            unmarshaller = context.createUnmarshaller();
            idData = (IdData) unmarshaller.unmarshal(reader);

            return idData.getCurrentId();

        } catch(IOException | JAXBException exception){
            throw new DAOException(exception);
        }
    }

    @Override
    public void writeCurrentId(int id) throws DAOException {
        IdData idData;
        JAXBContext context;
        Marshaller marshaller;

        try(FileOutputStream output = new FileOutputStream(currentIdFileName)) {

            idData = new IdData(id);
            context = JAXBContext.newInstance(IdData.class);
            marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(idData, output);

        } catch(IOException | JAXBException exception){
            throw new DAOException(exception);
        }
    }
}
