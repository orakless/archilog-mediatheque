package documents;

public interface Document {
    int numero();

    /**
     * @pre ni réservé ni emprunté
     **/
    void reservation(Abonne ab) throws ReservationException;

    /**
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    void emprunt(Abonne ab) throws EmpruntException;

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     **/
    void retour() throws RetourException;
}
