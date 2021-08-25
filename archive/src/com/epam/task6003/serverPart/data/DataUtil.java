package com.epam.task6003.serverPart.data;

import com.epam.task6003.serverPart.model.Student;
import com.epam.task6003.serverPart.model.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DataUtil {
    public static ArrayList<User> loadUsers(String fileName) throws IOException, JAXBException {
        FileInputStream reader = new FileInputStream(fileName);
        JAXBContext context = JAXBContext.newInstance(UsersData.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        UsersData usersData = (UsersData) unmarshaller.unmarshal(reader);

        usersData.setUsers((ArrayList<User>) usersData.getUsers()
                .stream()
                .peek(user -> user.setPassword(decryptPassword(user.getPassword())))
                .collect(Collectors.toList()));

        return usersData.getUsers();
    }

    public static ArrayList<Student> loadStudents(String fileName) throws IOException, JAXBException {
        FileInputStream reader = new FileInputStream(fileName);
        JAXBContext context = JAXBContext.newInstance(StudentsData.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StudentsData studentsData = (StudentsData) unmarshaller.unmarshal(reader);

        return studentsData.getStudents();
    }

    public static void unloadUsers(String fileName, ArrayList<User> usersList) throws FileNotFoundException, JAXBException {
        UsersData usersData = new UsersData(usersList);
        FileOutputStream output = new FileOutputStream(fileName);
        JAXBContext context = JAXBContext.newInstance(UsersData.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        usersData.setUsers((ArrayList<User>) usersData.getUsers()
                .stream()
                .peek(user -> user.setPassword(encryptPassword(user.getPassword())))
                .collect(Collectors.toList()));

        marshaller.marshal(usersData, output);
    }

    public static void unloadStudents(String fileName, ArrayList<Student> studentsList) throws FileNotFoundException, JAXBException {
        StudentsData studentsData = new StudentsData(studentsList);
        FileOutputStream output = new FileOutputStream(fileName);
        JAXBContext context = JAXBContext.newInstance(StudentsData.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(studentsData, output);
    }

    private static String decryptPassword(String encryptedPassword) {
        char[] encryptedPasswordChars = encryptedPassword.toCharArray();
        char[] symbols = "abcdefghijklmnopqrstuvwxyzABCDEGGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        char[] decryptedPasswordChars = new char[encryptedPasswordChars.length];
        String decryptedPassword;

        for (int i = 0; i < encryptedPassword.length(); i++) {
            for (int j = 0; j < 62; j++) {
                if (symbols[j] == encryptedPasswordChars[i]) {
                    decryptedPasswordChars[i] = symbols[(j + 7) % 62];
                }
            }
        }

        decryptedPassword = String.valueOf(decryptedPasswordChars);
        return decryptedPassword;
    }

    private static String encryptPassword(String decryptedPassword) {
        char[] decryptedPasswordChars = decryptedPassword.toCharArray();
        char[] symbols = "abcdefghijklmnopqrstuvwxyzABCDEGGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        char[] encryptedPasswordChars = new char[decryptedPasswordChars.length];
        String encryptedPassword;

        for (int i = 0; i < decryptedPassword.length(); i++) {
            for (int j = 0; j < 62; j++) {
                if (symbols[j] == decryptedPasswordChars[i]) {
                    encryptedPasswordChars[i] = ((j >= 7) ? symbols[j - 7] : symbols[62 + j - 7]);
                }
            }
        }

        encryptedPassword = String.valueOf(encryptedPasswordChars);
        return encryptedPassword;
    }
}
