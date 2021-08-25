package com.epam.task6003.clientPart.main;

import com.epam.task6003.clientPart.model.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

public class ClientArchiveProcessor {
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final Scanner scanner;

    public ClientArchiveProcessor(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        this.scanner = new Scanner(System.in);
    }

    void start() throws IOException, JAXBException {
        processInitializing();
        processStartMenu();
    }

    void processInitializing() throws IOException {
        String login;
        String password;
        String isUserInitialized;
        MenuShowerUtil.showTitle();
        System.out.print(" Enter login: ");
        login = scanner.nextLine();
        System.out.print(" Enter password: ");
        password = scanner.nextLine();
        writer.write(login);
        writer.newLine();
        writer.write(password);
        writer.newLine();
        writer.flush();
        isUserInitialized = reader.readLine();
        if (isUserInitialized.equals("-1")) {
            System.out.println("Incorrect data! Try again!");
            processInitializing();
        }
    }

    void processStartMenu() throws IOException, JAXBException {
        String userRole = reader.readLine();
        if (userRole.equals("1")) {
            processAdminMenu();
        } else if (userRole.equals("2")) {
            processUserMenu();
        } else {
            throw new RuntimeException("Incorrect UserRole!");
        }
    }

    void processUserMenu() throws IOException, JAXBException {
        String input;
        MenuShowerUtil.showUserStartMenu();
        input = scanner.nextLine();
        switch (input) {
            case "1" -> processViewingStudentsCaseForUser();
            case "0" -> {
                writer.write("exit\n");
                writer.flush();
                System.exit(0);
            }
        }
        processUserMenu();
    }

    private void processViewingStudentsCaseForUser() throws IOException, JAXBException {
        String input;
        Student student;
        Path studentFile;
        String answer;
        MenuShowerUtil.showViewingMenu();
        input = scanner.nextLine();
        if (!input.equals("0")) {
            writer.write("id=" + input + "\n");
            writer.flush();
            answer = reader.readLine();
            if (answer.equals("1")) {
                studentFile = acceptFile();
                student = unmarshalStudent(studentFile.toFile());
                System.out.println(student);
            } else {
                System.out.println("Student with such id does not exist!");
            }
            System.out.println(" - RETURN - any symbol");
            System.out.print("Enter here: ");
            scanner.nextLine();
        }
    }

    void processAdminMenu() throws IOException, JAXBException {
        String input;
        MenuShowerUtil.showAdminStartMenu();
        input = scanner.nextLine();
        switch (input) {
            case "1" -> processViewingStudentsCaseForAdmin();
            case "2" -> processAddStudent();
            case "0" -> {
                writer.write("exit\n");
                writer.flush();
                System.exit(0);
            }
        }
        processAdminMenu();
    }

    private void processViewingStudentsCaseForAdmin() throws IOException, JAXBException {
        String input;
        Student student = null;
        String answer;
        MenuShowerUtil.showViewingMenu();
        input = scanner.nextLine();
        if (!input.equals("0")) {
            writer.write("id=" + input + "\n");
            writer.flush();
            answer = reader.readLine();
            if (answer.equals("1")) {
                student = acceptStudentFromServer();
                System.out.println(student);
                System.out.println(" - CHANGE - \"1\"");
            } else {
                System.out.println("Student with such id does not exist!");
            }
            System.out.println(" - RETURN - any other symbol");
            System.out.print("Enter here: ");
            input = scanner.nextLine();
            if (student != null && input.equals("1")) {
                processChangeStudent(student);
            }
        }
    }

    private void processChangeStudent(Student student) throws JAXBException, IOException {
        String input;
        MenuShowerUtil.showChangeMenu(student.toString());
        input = scanner.nextLine();
        switch (input) {
            case "1" -> processChangeSurname(student);
            case "2" -> processChangeName(student);
            case "3" -> processChangePatronymic(student);
            case "4" -> processChangeBirthdayDate(student);
            case "5" -> processChangeGroup(student);
            case "6" -> processChangeCourse(student);
            default -> {
                return;
            }
        }

        System.out.println(" - Continue change - \"1\"");
        System.out.print(" - Enter any other symbol to return: ");
        input = scanner.nextLine();
        if (input.equals("1")) {
            processChangeStudent(student);
        }
    }

    private void processChangeSurname(Student student) throws JAXBException, IOException {
        String input;
        String oldSurname;
        String serverAnswer;

        oldSurname = student.getSurname();
        System.out.print("\n Enter new surname: ");
        input = scanner.nextLine();
        try {
            student.setSurname(input);
            sendStudentCaseToServer(student, "change");
            serverAnswer = reader.readLine();
            if (serverAnswer.equals("1")) {
                System.out.println("Changes saved.");
            } else {
                System.out.println("Error! Changes not saved!");
                student.setSurname(oldSurname);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void processChangeName(Student student) throws JAXBException, IOException {
        String input;
        String oldName;
        String serverAnswer;

        oldName = student.getName();
        System.out.print("\n Enter new name: ");
        input = scanner.nextLine();
        try {
            student.setName(input);
            sendStudentCaseToServer(student, "change");
            serverAnswer = reader.readLine();
            if (serverAnswer.equals("1")) {
                System.out.println("Changes saved.");
            } else {
                System.out.println("Error! Changes not saved!");
                student.setName(oldName);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void processChangePatronymic(Student student) throws JAXBException, IOException {
        String input;
        String oldPatronymic;
        String serverAnswer;

        oldPatronymic = student.getPatronymic();
        System.out.print("\n Enter new patronymic: ");
        input = scanner.nextLine();
        try {
            student.setPatronymic(input);
            sendStudentCaseToServer(student, "change");
            serverAnswer = reader.readLine();
            if (serverAnswer.equals("1")) {
                System.out.println("Changes saved.");
            } else {
                System.out.println("Error! Changes not saved!");
                student.setPatronymic(oldPatronymic);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void processChangeBirthdayDate(Student student) throws JAXBException, IOException {
        String input;
        LocalDate oldBirthdayDate;
        String serverAnswer;

        oldBirthdayDate = student.birthdayDate();
        System.out.print("\n Enter new birthday date (yyyy-mm-dd): ");
        input = scanner.nextLine();
        try {
            student.changeBirthdayDate(input);
            sendStudentCaseToServer(student, "change");
            serverAnswer = reader.readLine();
            if (serverAnswer.equals("1")) {
                System.out.println("Changes saved.");
            } else {
                System.out.println("Error! Changes not saved!");
                student.changeBirthdayDate(oldBirthdayDate);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void processChangeGroup(Student student) throws JAXBException, IOException {
        String input;
        int oldGroup;
        String serverAnswer;

        oldGroup = student.getGroup();
        System.out.print("\n Enter new group number: ");
        input = scanner.nextLine();
        try {
            student.setGroup(Integer.parseInt(input));
            sendStudentCaseToServer(student, "change");
            serverAnswer = reader.readLine();
            if (serverAnswer.equals("1")) {
                System.out.println("Changes saved.");
            } else {
                System.out.println("Error! Changes not saved!");
                student.setGroup(oldGroup);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void processChangeCourse(Student student) throws JAXBException, IOException {
        String input;
        int oldCourse;
        String serverAnswer;

        oldCourse = student.getCourse();
        System.out.print("\n Enter new course number: ");
        input = scanner.nextLine();
        try {
            student.setCourse(Integer.parseInt(input));
            sendStudentCaseToServer(student, "change");
            serverAnswer = reader.readLine();
            if (serverAnswer.equals("1")) {
                System.out.println("Changes saved.");
            } else {
                System.out.println("Error! Changes not saved!");
                student.setCourse(oldCourse);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void processAddStudent() throws IOException, JAXBException {
        Student student = new Student();
        String serverAnswer;
        String input;
        int id;

        MenuShowerUtil.showAddingMenu();
        try {
            readStudentData(student);

            writer.write("currentId\n");
            writer.flush();
            id = Integer.parseInt(reader.readLine());
            student.setId(id);

            sendStudentCaseToServer(student, "add");

            serverAnswer = reader.readLine();
            if (serverAnswer.equals("1")) {
                System.out.println("Student case added.");
            } else {
                System.out.println("Error on the server! Student case not added!");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("Student case not added! " + exception.getMessage());
        }

        System.out.println("\n - Add more - \"1\"");
        System.out.println(" - Return - any other");
        System.out.print("\nEnter here: ");
        input = scanner.nextLine();
        if (input.equals("1")) {
            processAddStudent();
        }
    }

    private void readStudentData(Student student) {
        String input;

        System.out.print("Surname: ");
        input = scanner.nextLine();
        student.setSurname(input);
        System.out.print("Name: ");
        input = scanner.nextLine();
        student.setName(input);
        System.out.print("Patronymic: ");
        input = scanner.nextLine();
        student.setPatronymic(input);
        System.out.print("BirthdayDate (yyyy-mm-dd): ");
        input = scanner.nextLine();
        student.changeBirthdayDate(input);
        System.out.print("Group: ");
        input = scanner.nextLine();
        student.setGroup(Integer.parseInt(input));
        System.out.print("Course: ");
        input = scanner.nextLine();
        student.setCourse(Integer.parseInt(input));
    }

    private Path acceptFile() throws IOException {
        Path studentFile = Files.createTempFile("student", "xml");
        byte[] fileByteArray = new byte[1024];
        FileOutputStream fos = new FileOutputStream(studentFile.toFile());
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int bytesRead = inputStream.read(fileByteArray, 0, fileByteArray.length);
        bos.write(fileByteArray, 0, bytesRead);
        bos.close();
        return studentFile;
    }

    private void sendStudentCaseToServer(Student student, String messageToServer) throws JAXBException, IOException {
        File file;
        byte[] fileByteArray;
        BufferedInputStream bis;
        int numberOfBytesRead;
        if (student != null) {
            writer.write(messageToServer + "\n");
            writer.flush();
            file = marshalStudent(student);
            fileByteArray = new byte[(int) file.length()];
            bis = new BufferedInputStream(new FileInputStream(file));
            numberOfBytesRead = bis.read(fileByteArray, 0, fileByteArray.length);
            outputStream.write(fileByteArray, 0, numberOfBytesRead);
            outputStream.flush();
        } else {
            throw new RuntimeException("Cannot be sent to the server! Student == null!");
        }
    }

    private Student acceptStudentFromServer() throws JAXBException, IOException {
        File file = acceptFile().toFile();
        return unmarshalStudent(file);
    }

    public File marshalStudent(Student student) throws JAXBException, IOException {
        Path studentFile = Files.createTempFile("tempStudent", "xml");
        FileOutputStream fileOutputStream = new FileOutputStream(studentFile.toFile());
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(student, fileOutputStream);
        fileOutputStream.close();
        return studentFile.toFile();
    }

    private Student unmarshalStudent(File file) throws JAXBException, FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Student) unmarshaller.unmarshal(fileInputStream);
    }
}
