package server.entities.implementations;

import server.entities.Abonne;
import server.entities.document.Document;
import server.errors.EmpruntException;
import server.errors.ReservationException;
import server.errors.RetourException;
import server.structures.Mediatheque;

import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public abstract class DocumentImpl implements Document {
    private static final long RESERVATION_TIME_LIMIT = 90 * 60 * 1000;
    int numero;
    Abonne emprunteur = null;
    Abonne reserveur = null;
    Date reservationTimeLimit = null;

    Timer reservationTimer = null;

    public DocumentImpl(int numero) {
        this.numero = numero;
    }

    private class ReservationTimer extends TimerTask {
        private Document document;

        public ReservationTimer(Document document) {
            this.document = document;
        }

        @Override
        public void run() {
            try {
                this.document.reservation(null);
            } catch (ReservationException e) {
                System.err.println("N'a pas pu retirer la réservation pour le document " + document.numero());
            }
        }
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
        if (ab != null && (reserveur != null || emprunteur != null))
            throw new ReservationException("Document non réservable.");
        this.reserveur = ab;
        this.reservationTimer = null;
        this.reservationTimeLimit = null;
        if (ab != null) {
            reservationTimeLimit = new Date(System.currentTimeMillis() + RESERVATION_TIME_LIMIT);
            reservationTimer = new Timer();
            reservationTimer.schedule(new ReservationTimer(this), RESERVATION_TIME_LIMIT);
        }
    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonne ab) throws EmpruntException {
        synchronized (this) {
            if (reserveur != null && reserveur != ab)
                throw new EmpruntException("Ce document est déjà réservé jusqu'à " + DateFormat.getInstance().format(reservationTimeLimit));
            if (emprunteur != null)
                throw new EmpruntException("Ce document est déjà emprunté.");
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
