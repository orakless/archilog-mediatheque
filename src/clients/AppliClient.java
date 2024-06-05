package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AppliClient {
    private final static String HOST = "localhost";

    public static void main(String[] args) {
        Socket socket;
        if(args.length != 1) {
            System.err.println("Pour lancer le programme il faut qu'il y ait au moins un argument, le numéro du PORT");
            System.exit(1);
        }
        try {
            socket = new Socket(HOST, Integer.parseInt(args[0]));
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true);

            String line = "";
            do {
                if (!line.isEmpty()) sOut.println(line);
                line = sIn.readLine();
                while(line != null && !line.equals("\uE000")) {
                    System.out.println(line);
                    line = sIn.readLine();
                }
                System.out.print("Réponse (Entrée pour fermer le programme) : ");
                line = clavier.readLine();
            } while (!line.isEmpty());

            socket.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(socket != null) {
            try {
                socket.close();
            } catch (IOException ignored){}
        }
    }
}
