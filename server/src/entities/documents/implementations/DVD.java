package entities.documents.implementations;

import entities.Abonne;
import entities.documents.Document;
import errors.EmpruntException;
import errors.ReservationException;
import errors.RetourException;

public class DVD extends DocumentImpl {
    public final static int ADULT_MIN_AGE = 16;
    boolean adulte;
    String titre;
    public DVD(int numero, String titre, boolean adult) {
        super(numero);
        this.titre = titre;
        this.adulte = adulte;
    }

    /**
     * @param ab
     * @pre ni réservé ni emprunté
     */
    @Override
    public void reservation(Abonne ab) throws ReservationException {
        if (ab.getAge() < ADULT_MIN_AGE)
            throw new ReservationException("L'usager n'a pas l'âge requis pour réserver ce document.");
        super.reservation(ab);
    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonne ab) throws EmpruntException {
        if (ab.getAge() < ADULT_MIN_AGE)
            throw new EmpruntException("L'usager n'a pas l'âge requis pour emprunter ce document.");
        super.emprunt(ab);
    }

    @Override
    public String toString() {
        return "DVD [#" + numero + "] " + titre;
    }
}
