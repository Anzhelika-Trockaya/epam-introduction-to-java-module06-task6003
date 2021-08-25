package com.epam.task6003.serverPart.main;

import com.epam.task6003.serverPart.data.DataUtil;
import com.epam.task6003.serverPart.model.Student;
import com.epam.task6003.serverPart.model.User;
import com.epam.task6003.serverPart.model.UserRole;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ServerArchiveProcessor {
    private final ArrayList<Student> students;
    private final ArrayList<User> users;
    private User currentUser;

    private final BufferedReader reader;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final BufferedWriter writer;

    private boolean endingSession = false;

    public ServerArchiveProcessor(InputStream inputStream, OutputStream outputStream, ArrayList<User> users, ArrayList<Student> students) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        reader = new BufferedReader(new InputStreamReader(inputStream));
        writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        this.users=users;
        this.students=students;
    }

    public void start(String studentsFileName) throws IOException, JAXBException {
        initializeUser();
        processStartMenu();
        while (!endingSession) {
            try {
                processRequests(studentsFileName);
            } catch(SocketException exc){
                endingSession=true;
            }
        }
    }



    public void initializeUser() throws IOException {
        String login;
        String password;

        login = reader.readLine();
        password = reader.readLine();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                currentUser = user;
                writer.write("0\n");
                writer.flush();
                break;
            }
        }
        if (currentUser == null) {
            writer.write("-1\n");
            writer.flush();
            initializeUser();
        }
    }

    public void processStartMenu() throws IOException {
        if (currentUser.getRole() == UserRole.ADMIN) {
            writer.write("1\n");
        } else if (currentUser.getRole() == UserRole.USER) {
            writer.write("2\n");
        } else {
            throw new RuntimeException("Incorrect UserRole");
        }
        writer.flush();
    }

    private void processRequests(String studentsFileName) throws IOException, JAXBException {
        String request = reader.readLine();
        if (request.matches("^id=(\\d+)$")) {
            sendStudentCase(request);
        } else if (request.equals("change")) {
            changeStudent();
            DataUtil.unloadStudents(studentsFileName, students);
        } else if (request.equals("currentId")) {
            writer.write(Student.currentId() + "\n");
            writer.flush();
        } else if (request.equals("add")) {
            addStudent();
            DataUtil.unloadStudents(studentsFileName, students);
        } else if(request.equals("exit")){
            endingSession = true;
        }
    }

    private void sendStudentCase(String request) throws JAXBException, IOException {
        int id = Integer.parseInt(request.substring(3));
        Student student = getStudent(id);
        File file;
        byte[] fileByteArray;
        BufferedInputStream bis;
        int numberOfBytesRead;
        if (student != null) {
            writer.write("1\n");
            writer.flush();
            file = marshalStudent(student);
            fileByteArray = new byte[(int) file.length()];
            bis = new BufferedInputStream(new FileInputStream(file));
            numberOfBytesRead = bis.read(fileByteArray, 0, fileByteArray.length);
            outputStream.write(fileByteArray, 0, numberOfBytesRead);
            outputStream.flush();
        } else {
            writer.write("0\n");
            writer.flush();
        }
    }

    private void changeStudent() throws IOException, JAXBException {
        File file = acceptFile().toFile();
        Student changedStudent = unmarshalStudent(file);
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == changedStudent.getId()) {
                students.set(i, changedStudent);
                writer.write("1\n");
                writer.flush();
                return;
            }
        }
        writer.write("0\n");
        writer.flush();
    }


    private void addStudent() throws IOException, JAXBException {
        File file = acceptFile().toFile();
        Student student = unmarshalStudent(file);
        if (student != null && !existsStudentWithId(student.getId())) {
            students.add(student);
            Student.changeCurrentId(student.getId() + 1);
            writer.write("1\n");
            writer.flush();
            return;
        }
        writer.write("0\n");
        writer.flush();
    }

    private boolean existsStudentWithId(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
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

    private Student getStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public static File marshalStudent(Student student) throws JAXBException, IOException {
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
