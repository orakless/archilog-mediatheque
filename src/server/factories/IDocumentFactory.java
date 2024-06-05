package server.factories;

import server.entities.document.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDocumentFactory {
    Document constructDocument(ResultSet result) throws SQLException;
}
