package factories;

import entities.documents.Document;
import entities.documents.implementations.DVD;

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
                        result.getString("titre"),
                        result.getBoolean("adult"));
            }
            default: {
                System.err.println("Type " + result.getString("type") + " not implemented.");
                return null;
            }
        }
    }
}
