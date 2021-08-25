package com.epam.task6003.clientPart.main;

import javax.xml.bind.JAXBException;
import java.net.*;
import java.io.*;

//Задание 3: создайте клиент-серверное приложение “Архив”.
//Общие требования к заданию:
//• В архиве хранятся Дела (например, студентов). Архив находится на сервере.
//• Клиент, в зависимости от прав, может запросить дело на просмотр, внести в
//него изменения, или создать новое дело.
//Требования к коду лабораторной работы:
//• Для реализации сетевого соединения используйте сокеты.
//• Формат хранения данных на сервере – xml-файлы.

public class ClientMain {
    private static Socket socket;

    public static void main(String[] args) throws IOException, JAXBException {
        socket = new Socket("127.0.0.1", 8000);
        InputStream inputStream= socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        ClientArchiveProcessor clientArchiveProcessor = new ClientArchiveProcessor(inputStream, outputStream);

        clientArchiveProcessor.start();

        outputStream.write("exit".getBytes());
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
