package by.epam.task6003.serverPart.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnection {
    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ClientConnection() throws IOException {
        serverSocket = new ServerSocket(8000);
    }

    public void accept() throws IOException {
        clientSocket = serverSocket.accept();
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }

    public String getRequest() throws IOException {
        StringBuilder requestBuilder;

        requestBuilder = new StringBuilder(reader.readLine());

        while (reader.ready()) {
            requestBuilder.append('\n');
            requestBuilder.append(reader.readLine());
        }

        return requestBuilder.toString();
    }

    public void sendResponse(String response) throws IOException {
        writer.write(response);
        writer.newLine();
        writer.flush();
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
        clientSocket.close();
    }
}
