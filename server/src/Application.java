import factories.DocumentFactory;
import structures.Mediatheque;

public class Application {
    private final static int RESERVATION_PORT = 3000;
    private final static int EMPRUNT_PORT = 4000;
    private final static int RETOUR_PORT = 5000;
    public static void main(String[] args) {
        Mediatheque mediatheque = Mediatheque.getInstance();
        mediatheque.populate(new DocumentFactory());
    }
}
