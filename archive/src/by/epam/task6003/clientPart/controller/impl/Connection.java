package by.epam.task6003.clientPart.controller.impl;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private String host;
    private int port;
    private BufferedReader reader;
    private BufferedWriter writer;


    public Connection() throws IOException {
        host = "127.0.0.1";
        port = 8000;
        socket = new Socket(host, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void sendRequest(String request) throws IOException {
        writer.write(request);
        writer.newLine();
        writer.flush();
    }

    public String getResponse() throws IOException {
        StringBuilder responseBuilder;

        responseBuilder = new StringBuilder(reader.readLine());

        while (reader.ready()) {
            responseBuilder.append('\n');
            responseBuilder.append(reader.readLine());
        }

        return responseBuilder.toString();
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }

}
