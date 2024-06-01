package entities.documents.implementations;

import entities.Abonne;
import entities.documents.Document;
import errors.EmpruntException;
import errors.ReservationException;
import errors.RetourException;
import structures.Mediatheque;

public abstract class DocumentImpl implements Document {
    int numero;
    Abonne emprunteur;
    Abonne reserveur;
    public DocumentImpl(int numero) {
        this.numero = numero;
        this.emprunteur = null;
        this.reserveur = null;
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
        synchronized (this) {
            if (emprunteur != null)
                throw new EmpruntException("Ce document est déjà emprunté");
            this.emprunteur = ab;
            Mediatheque mediatheque = Mediatheque.getInstance();
            mediatheque.insertDocument(this.numero, this);
        }
    }

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     **/
    @Override
    public void retour() throws RetourException {
        synchronized (this) {
            if (this.emprunteur == null)
                throw new RetourException("Ce document n'est pas emprunté.");
            this.emprunteur = null;
            Mediatheque mediatheque = Mediatheque.getInstance();
            mediatheque.insertDocument(this.numero, this);
        }
    }

    @Override
    public String toString() {
        return "Document non-identifié [#" + numero + "]";
    }
}
