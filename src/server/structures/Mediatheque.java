package server.structures;

import server.entities.Abonne;
import server.entities.document.Document;
import server.entities.wrappers.EntityWrapper;
import server.factories.IDocumentFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Mediatheque {
    private EntityWrapper<Document> documents;
    private EntityWrapper<Abonne> abonnes;

    private static Mediatheque mediatheque;

    public static Mediatheque getInstance () {
        if (mediatheque == null) {
            Mediatheque.mediatheque = new Mediatheque();
        }
        return Mediatheque.mediatheque;
    }

    public void populate(IDocumentFactory factory) {
        Database db = Database.getInstance();
        db.setFactory(factory);
        try {
            this.documents = new EntityWrapper<>(db.importDocument());
            this.abonnes = new EntityWrapper<>(db.importAbonne());
        } catch (SQLException e) {
            System.err.println("Could not initialize in-memory object storages. Quitting now.\nError: "
                    + e.getMessage());
            System.exit(1);
        }
    }
    public Optional<Document> getDocumentById(int id) {
        return documents.getEntityById(id);
    }

    public Optional<Abonne> getAbonneById(int id) {
        return abonnes.getEntityById(id);
    }

    public void insertDocument(int id, Document document) {
        this.documents.insertEntity(id, document);
    }

    public void insertAbonne(int id, Abonne abonne) {
        this.abonnes.insertEntity(id, abonne);
    }

    public List<Document> getDocuments() {
        return this.documents.getEntityList();
    }

    public List<Abonne> getAbonnes() {
        return this.abonnes.getEntityList();
    }
}
