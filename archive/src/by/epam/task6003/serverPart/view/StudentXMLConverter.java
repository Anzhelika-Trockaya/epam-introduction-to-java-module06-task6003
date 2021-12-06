package by.epam.task6003.serverPart.view;

import by.epam.task6003.serverPart.bean.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class StudentXMLConverter {
    private static StudentXMLConverter instance=new StudentXMLConverter();

    private StudentXMLConverter(){
    }

    public static StudentXMLConverter getInstance(){
        return instance;
    }

    public String marshalStudent(Student student) throws JAXBException, IOException {
        ByteArrayOutputStream outputStream;
        JAXBContext context;
        Marshaller marshaller;

        outputStream = new ByteArrayOutputStream();

        context = JAXBContext.newInstance(Student.class);
        marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(student, outputStream);
        outputStream.close();

        return outputStream.toString();
    }

    public Student unmarshalStudent(String studentXMLString) throws JAXBException {
        ByteArrayInputStream inputStream;
        JAXBContext context;
        Unmarshaller unmarshaller;

        inputStream = new ByteArrayInputStream(studentXMLString.getBytes());
        context = JAXBContext.newInstance(Student.class);
        unmarshaller = context.createUnmarshaller();

        return (Student) unmarshaller.unmarshal(inputStream);
    }
}
