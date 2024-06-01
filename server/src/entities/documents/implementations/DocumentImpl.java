package entities.documents.implementations;

import entities.Abonne;
import entities.documents.Document;
import errors.EmpruntException;
import errors.ReservationException;
import errors.RetourException;

public abstract class DocumentImpl implements Document {
    int numero;

    public DocumentImpl(int numero) {
        this.numero = numero;
    }

    @Override
    public int numero() {
        return this.numero;
    }

    /**
     * @param ab
     * @pre ni réservé ni emprunté
     */
    @Override
    public void reservation(Abonne ab) throws ReservationException {
        return;
    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonne ab) throws EmpruntException {
        return;
    }

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     **/
    @Override
    public void retour() throws RetourException {
        return;
    }
}
