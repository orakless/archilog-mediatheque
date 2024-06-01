package factories;

import entities.documents.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDocumentFactory {
    Document constructDocument(ResultSet result) throws SQLException;
}
