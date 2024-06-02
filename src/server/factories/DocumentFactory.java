package server.factories;

import server.entities.documents.Document;
import server.entities.documents.implementations.DVD;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentFactory implements IDocumentFactory {
    public DocumentFactory() {};

    public Document constructDocument(ResultSet result)
        throws SQLException {
        switch(result.getString("type")) {
            case "dvd": {
                return new DVD(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getBoolean("adult"));
            }
            default: {
                System.err.println("Type " + result.getString("type") + " non implémenté.");
                return null;
            }
        }
    }
}
