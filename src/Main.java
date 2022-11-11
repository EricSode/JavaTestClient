import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Socket socket = new Socket("25.35.35.92", 4444)) {
            BufferedReader input = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String userInput;
            String response;
            String clientName = "empty";
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();

            do {
                if (clientName.equals("empty")) {
                    System.out.println("Enter your name: ");
                    userInput = scanner.nextLine();
                    clientName = userInput;
                    output.println(userInput);
                    if (userInput.equals("exit")) {
                        break;
                    }
                } else {
                    String message = ("" + clientName + ": ");
                    System.out.println(message);
                    userInput = scanner.nextLine();
                    output.println(message + " " + userInput);
                    if (userInput.equals("exit")) {
                        break;
                    }
                }


            } while (!userInput.equals("exit"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}