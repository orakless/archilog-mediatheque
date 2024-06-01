package structures;

import entities.Abonne;
import entities.documents.Document;
import entities.wrappers.EntityWrapper;
import factories.IDocumentFactory;

import javax.print.attribute.standard.Media;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
    public Document getDocumentById(int id) {
        return documents.getEntityById(id);
    }

    public Abonne getAbonneById(int id) {
        return abonnes.getEntityById(id);
    }

    public void insertDocument(int id, Document document) {
        this.documents.insertEntity(id, document);
    }

    public void insertAbonne(int id, Abonne abonne) {
        this.abonnes.insertEntity(id, abonne);
    }
}
