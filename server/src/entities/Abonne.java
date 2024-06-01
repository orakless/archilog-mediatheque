package entities;

import java.sql.Date;

public class Abonne {
    int id;
    String nom;
    Date dateNaissance;

    public Abonne(int id, String nom, Date dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    };

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }
}
