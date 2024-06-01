package repositories;

import entities.documents.Document;

public interface IDocumentRepository {

    Document getDocument(String id);
}
