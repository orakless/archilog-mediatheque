package server.structures;

import server.entities.Abonne;
import server.entities.documents.Document;
import server.factories.IDocumentFactory;

import java.sql.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Database {
    private Connection connect;
    private static Database database;

    private IDocumentFactory factory;

    public static Database getInstance() {
        if (database == null) {
            Database.database = new Database();
        }
        return Database.database;
    }

    private Database() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connect = DriverManager.getConnection("jdbc:postgresql:mediatheque", "archilog", "archilog");
        } catch (SQLException e) {
            System.err.println("Could not initialize database connection" + e.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found.");
            System.exit(1);
        }
    }

    public void setFactory(IDocumentFactory factory) {
        this.factory = factory;
    }

    public ConcurrentMap<Integer, Document> importDocument() throws SQLException {
        ConcurrentMap<Integer, Document> documents = new ConcurrentHashMap<>();
        PreparedStatement getDocuments = connect.prepareStatement("SELECT * FROM documents");
        ResultSet result = getDocuments.executeQuery();
        while (result.next()) {
            documents.put(result.getInt("id"), factory.constructDocument(result));
        }
        return documents;
    }

    public ConcurrentMap<Integer, Abonne> importAbonne() throws SQLException {
        ConcurrentMap<Integer, Abonne> abonnes = new ConcurrentHashMap<>();
        PreparedStatement getDocuments = connect.prepareStatement("SELECT * FROM utilisateurs");
        ResultSet result = getDocuments.executeQuery();
        while (result.next()) {
            abonnes.put(result.getInt("id"),
                    new Abonne(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getDate("birthdate")
                    ));
        }
        return abonnes;
    }
}
