package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AppliClient {
    private final static String HOST = "localhost";
    private static int PORT;


    public static void main(String[] args) {
        Socket socket = null;
        if(args.length != 1) {
            System.err.println("Pour lancer le programme il faut qu'il y est au moins argument, le numéro du PORT");
            System.exit(1);
        }
        try {
            socket = new Socket(HOST, Integer.parseInt(args[0]));
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true);
            String line = null;

            while(!line.equals("")) {
                if(line != null){
                    sOut.println(line);
                }
                while(!sIn.readLine().equals("\u2029")) {
                    System.out.println(sIn.readLine());
                    sOut.println(clavier.readLine());
                }
                line = clavier.readLine();
            }

            socket.close();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(socket != null) {
            try{
                socket.close();
            }catch (IOException ignored){}
        }
    }
}
