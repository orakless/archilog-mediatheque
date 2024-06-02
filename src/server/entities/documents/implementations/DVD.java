package server.entities.documents.implementations;

import server.entities.Abonne;
import server.entities.documents.Document;
import server.errors.EmpruntException;
import server.errors.ReservationException;
import server.errors.RetourException;

public class DVD extends DocumentImpl {
    public final static int ADULT_MIN_AGE = 16;
    boolean adult;
    String titre;
    public DVD(int numero, String titre, boolean adult) {
        super(numero);
        this.titre = titre;
        this.adult = adult;
    }

    /**
     * @param ab
     * @pre ni réservé ni emprunté
     */
    @Override
    public void reservation(Abonne ab) throws ReservationException {
        if (adult && ab.getAge() < ADULT_MIN_AGE)
            throw new ReservationException("L'usager n'a pas l'âge requis pour réserver ce document.");
        super.reservation(ab);
    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonne ab) throws EmpruntException {
        if (adult && ab.getAge() < ADULT_MIN_AGE)
            throw new EmpruntException("L'usager n'a pas l'âge requis pour emprunter ce document.");
        super.emprunt(ab);
    }

    @Override
    public String toString() {
        return "DVD [#" + numero + ((super.emprunteur != null) ? ", emprunté]" : "] ") + titre;
    }
}
