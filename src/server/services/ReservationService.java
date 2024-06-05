package server.services;

import com.unodos.Service;
import server.entities.Abonne;
import server.entities.document.Document;
import server.errors.ReservationException;
import server.errors.RetourException;
import server.structures.Mediatheque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

import static server.services.CommonServiceConfig.TRANSMISSION_END;

public class ReservationService extends Service {
    public ReservationService(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        Mediatheque mediatheque = Mediatheque.getInstance();
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(super.getSocket().getInputStream()));
            PrintWriter out = new PrintWriter(super.getSocket().getOutputStream(),
                    true);

            out.println("Bonjour. Vous êtes sur le service de réservation de document de la médiathèque.");

            out.println("Votre numéro d'abonné : ");
            out.println(TRANSMISSION_END);
            int abonneId = Integer.parseInt(in.readLine());

            Optional<Abonne> oAbonne = mediatheque.getAbonneById(abonneId);
            while (oAbonne.isEmpty()) {
                out.println("Abonné inexistant.");
                out.println("Votre numéro d'abonné : ");
                out.println(TRANSMISSION_END);
                abonneId = Integer.parseInt(in.readLine());
                oAbonne = mediatheque.getAbonneById(abonneId);
            }
            Abonne abonne = oAbonne.get();

            out.println("Votre numéro de document : ");
            out.println(TRANSMISSION_END);
            int documentId = Integer.parseInt(in.readLine());

            Optional<Document> oDocument = mediatheque.getDocumentById(documentId);

            while (oDocument.isEmpty()) {
                out.println("Document inexistant.");
                out.println("Votre numéro de document : ");
                out.println(TRANSMISSION_END);
                documentId = Integer.parseInt(in.readLine());
                oDocument = mediatheque.getDocumentById(documentId);
            }
            Document document = oDocument.get();
            try {
                document.reservation(abonne);
            } catch (ReservationException e) {
                out.println("Le document n'a pas pu être réservé pour la raison suivante : " + e.getMessage());
                super.getSocket().close();
            }
            mediatheque.insertDocument(documentId, document);
            out.println("Document réservé avec succès.");
            super.getSocket().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
