package entities.wrappers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

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
