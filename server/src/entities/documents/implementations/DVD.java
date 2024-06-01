package entities.documents.implementations;

import entities.Abonne;
import entities.documents.Document;
import errors.EmpruntException;
import errors.ReservationException;
import errors.RetourException;

public class DVD implements Document {
    boolean adulte;
    int numero;

    public DVD(int numero, boolean adulte) {
        this.adulte = adulte;
    }

    @Override
    public int numero() {
        return numero;
    }

    /**
     * @param ab
     * @pre ni réservé ni emprunté
     */
    @Override
    public void reservation(Abonne ab) throws ReservationException {

    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonne ab) throws EmpruntException {

    }

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     **/
    @Override
    public void retour() throws RetourException {

    }
}
