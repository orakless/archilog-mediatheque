package entities.wrappers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/***
 * Le but de cette classe est de permettre l'encapsulation des données, et d'assurer qu'il n'y ait
 * jamais d'utilisation concurrente "nocive" au sein d'une même ConcurrentMap.
 * Normalement, une ConcurrentMap réalise déjà ce travail, mais nous avons besoin d'afficher les données
 * de la map à certains moments (notamment pour lister les entités à l'utilisateur), sachant qu'il n'y
 * a pas de moyen thread-safe d'itérer sur tous les éléments de la map dans les implémentations  (les informations récupérées
 * pourraient ne pas être à jour).
 * @param <T>
 */
public class EntityWrapper<T> {
    private ConcurrentMap<Integer, T> entities;

    public EntityWrapper(ConcurrentMap<Integer, T> entities) {
        this.entities = entities;
    }

    public List<T> getEntityList() {
        List<T> entityList = new ArrayList<>();
        synchronized (this) {
            for (int id: entities.keySet()) {
                entityList.add(entities.get(id));
            }
        }
        return entityList;
    }
    public T getEntityById(int id) {
        synchronized (this) {
            return entities.getOrDefault(id, null);
        }
    }

    public void insertEntity(int id, T entity) {
        synchronized (this) {
            entities.put(id, entity);
        }
    }
}
