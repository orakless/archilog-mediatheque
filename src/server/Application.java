package server;

import server.entities.Abonne;
import server.entities.documents.Document;
import server.errors.EmpruntException;
import server.errors.ReservationException;
import server.errors.RetourException;
import server.factories.DocumentFactory;
import server.structures.Mediatheque;

import java.util.TimerTask;

public class Application {
    private final static int RESERVATION_PORT = 3000;
    private final static int EMPRUNT_PORT = 4000;
    private final static int RETOUR_PORT = 5000;

    public static void main(String[] args) {
        Mediatheque mediatheque = Mediatheque.getInstance();
        mediatheque.populate(new DocumentFactory());

        for (Document doc: mediatheque.getDocuments()) {
            System.out.println(doc.toString());
        }

        Document document = mediatheque.getDocumentById(1);
        Abonne abonne = mediatheque.getAbonneById(1);
        try {
            document.emprunt(abonne);
        } catch (EmpruntException e) {
            System.err.println(e.getMessage());
        }

        for (Document doc: mediatheque.getDocuments()) {
            System.out.println(doc.toString());
        }

        try {
            document.emprunt(abonne);
        } catch (EmpruntException e) {
            System.err.println(e.getMessage());
        }

        try {
            document.retour();
        } catch (RetourException e) {
            System.err.println(e.getMessage());
        }

        for (Document doc: mediatheque.getDocuments()) {
            System.out.println(doc.toString());
        }
    }
}
