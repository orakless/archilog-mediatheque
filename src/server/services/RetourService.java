package server.services;

import com.unodos.Service;
import server.entities.document.Document;
import server.errors.RetourException;
import server.structures.Mediatheque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

import static server.services.CommonServiceConfig.TRANSMISSION_END;

public class RetourService extends Service {
    public RetourService(Socket socket) {
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

            out.println("Bonjour. Vous êtes sur le service de retour de document de la médiathèque.");

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
                document.retour();
            } catch (RetourException e) {
                out.println("Le document n'a pas pu être retourné pour la raison suivante : " + e.getMessage());
                super.getSocket().close();
            }
            mediatheque.insertDocument(documentId, document);
            out.println("Document retourné avec succès.");
            super.getSocket().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
