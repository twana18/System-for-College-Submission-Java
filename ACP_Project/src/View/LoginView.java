package Views;

import Models.Adminstrator;
import Models.SendOrReceiveData;
import Models.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class LoginView {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 4444)) {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Scanner input = new Scanner(System.in);

            boolean isUserFound = false;
            System.out.println("Welcome to System for college submission program\n");
            while (!isUserFound) {
                System.out.println("Enter Your ID: ");
                String id = input.nextLine();
                System.out.println("Enter Your Password: ");
                String password = input.nextLine();

                try {
                    //send id and password to the server
                    outputStream.writeObject(new SendOrReceiveData<>("find-user", Arrays.asList(id, password)));
                    outputStream.flush();

                    //read the server message that may contain an object of Student or Adminstrator
                    SendOrReceiveData<String, Object> receivedDataFromServer = (SendOrReceiveData<String, Object>) inputStream.readObject();
                    if (!receivedDataFromServer.getCommand().equals("find-user-failed")) {
                        if (receivedDataFromServer.getObject() instanceof Adminstrator) {
                            isUserFound = true;
                            inputStream.close();
                            outputStream.close();
                            socket.close();
                            AdminstratorView adminstratorView = new AdminstratorView((Adminstrator) receivedDataFromServer.getObject());
                        } else if (receivedDataFromServer.getObject() instanceof Student) {
                            isUserFound = true;
                            inputStream.close();
                            outputStream.close();
                            socket.close();
                            StudentView studentView = new StudentView((Student) receivedDataFromServer.getObject());
                        } else {
                            System.err.println("Wrong data provided, try add correct ID and Password");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("cannot send data(id, password) to the server: " + e.getMessage() + e.getCause());
                } catch (ClassNotFoundException e) {
                    System.out.println("Cannot read data from server: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Server Exception: " + e.getMessage());
        }
    }
}
