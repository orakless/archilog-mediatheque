package server;

import com.unodos.Server;
import server.factories.DocumentFactory;
import server.services.EmpruntService;
import server.services.ReservationService;
import server.services.RetourService;
import server.structures.Mediatheque;

import java.io.IOException;

public class Application {
    private final static int RESERVATION_PORT = 3000;
    private final static int EMPRUNT_PORT = 4000;
    private final static int RETOUR_PORT = 5000;

    public static void main(String[] args) {
        Mediatheque mediatheque = Mediatheque.getInstance();
        mediatheque.populate(new DocumentFactory());

        try {
            new Thread(new Server(ReservationService.class, RESERVATION_PORT)).start();
            System.out.println("Serveur de réservation lancé sur le port " + RESERVATION_PORT);
            new Thread(new Server(EmpruntService.class, EMPRUNT_PORT)).start();
            System.out.println("Serveur d'emprunt lancé sur le port " + EMPRUNT_PORT);
            new Thread(new Server(RetourService.class, RETOUR_PORT)).start();
            System.out.println("Server de retour lancé sur le port " + RETOUR_PORT);
        } catch (IOException e) {
            System.err.println("Erreur lors de la céation du serveur : " + e.getMessage());
        }
    }
}
