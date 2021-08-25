package com.epam.task6003.serverPart.main;

import com.epam.task6003.serverPart.data.DataUtil;
import com.epam.task6003.serverPart.model.Student;
import com.epam.task6003.serverPart.model.User;

import javax.xml.bind.JAXBException;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

//Задание 3: создайте клиент-серверное приложение “Архив”.
//Общие требования к заданию:
//• В архиве хранятся Дела (например, студентов). Архив находится на сервере.
//• Клиент, в зависимости от прав, может запросить дело на просмотр, внести в
//него изменения, или создать новое дело.
//Требования к коду лабораторной работы:
//• Для реализации сетевого соединения используйте сокеты.
//• Формат хранения данных на сервере – xml-файлы.

public class ServerMain {
    private static ArrayList<Student> students;
    private static ArrayList<User> users;

    private static final String studentsFileName = "archive/src/com/epam/task6003/serverPart/data/studentsData.xml";
    private static final String usersFileName = "archive/src/com/epam/task6003/serverPart/data/usersData.xml";

    public static void main(String[] args) throws IOException, JAXBException {
        ServerSocket serverSocket = new ServerSocket(8000);
        ServerArchiveProcessor serverArchiveProcessor;
        loadData();
        setStudentsCurrentId();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            serverArchiveProcessor = new ServerArchiveProcessor(inputStream, outputStream, users, students);
            serverArchiveProcessor.start(studentsFileName);

            inputStream.close();
            outputStream.close();
            clientSocket.close();
        }
    }

    private static void loadData() throws IOException, JAXBException {
        users = DataUtil.loadUsers(usersFileName);
        students = DataUtil.loadStudents(studentsFileName);
        System.out.println("Data loaded.");
    }

    private static void setStudentsCurrentId() {
        for (Student student : students) {
            if (student.getId() >= Student.currentId()) {
                Student.changeCurrentId(student.getId() + 1);
            }
        }
    }

}
