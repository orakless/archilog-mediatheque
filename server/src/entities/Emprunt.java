package entities;

import entities.documents.Document;

import java.sql.Date;

public class Emprunt {
    Document document;
    Abonne abonne;
    Date finEmprunt;

    public Emprunt(Abonne abonne, Document document, Date finEmprunt) {
        this.document = document;
        this.abonne = abonne;
        this.finEmprunt = finEmprunt;
    }
}
