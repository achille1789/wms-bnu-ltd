package backend;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import backend.entities.Entity;
import backend.entities.Data;

/**
 * A class that handles the list of Entities.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public abstract class EntitiesList {
    // The fields.
    protected List<Entity> entitiesList = new LinkedList<>();
    
    /**
     * Get the list of entities.
     * @param type of log.
     * @param message to log.
     */
    protected void printlog(LogType type, String message) {
        Logger.debug(message);
    }

    /**
     * Get the list of entities.
     * @return the list of entities.
     */
    public List<Entity> getEntitiesList() {
        return entitiesList;
    }

    /**
     * Get data of the passed Entities.
     * @param name the name of the entity
     * @param surname the surname of the entity
     * @return a HashMap with all the entity data
     */
    public HashMap<Data, String> getEntityData(String id) {
        HashMap<Data, String> entityData = null;
        for (int i = 0; i < entitiesList.size(); i++) {
            if (id.equals(entitiesList.get(i).getId())) {
                entityData = entitiesList.get(i).getAllData();
                break;
            }
        }
        if (entityData == null) {
            printlog(LogType.ERROR, "Entity not found");
        }
        return entityData;
    }

    /**
     * Update data of the passed Entities.
     * @param id the id of the entity
     * @param key the key of the data to update
     * @param value the new value of the data
     */
    public void updateEntityData(String id, Data key, String value) {
        boolean found = false;
        for (int i = 0; i < entitiesList.size(); i++) {
            if (id.equals(entitiesList.get(i).getId())) {
                entitiesList.get(i).update(key, value);
                printlog(LogType.INFO, "Entity data updated: " + entitiesList.get(i).getAllData().toString());
                found = true;
                break;
            }
        }
        if (!found) {
            printlog(LogType.ERROR, "Entity not found, data not updated");
        }
    }

    /**
     * Delete passed Entities.
     * @param id the id of the entity
     */
    public void deleteEntity(String id) {
        boolean found = false;
        for (int i = 0; i < entitiesList.size(); i++) {
            if (id.equals(entitiesList.get(i).getId())) {
                entitiesList.remove(i);
                printlog(LogType.INFO, "Entity deleted");
                found = true;
                break;
            }
        }
        if (!found) {
            printlog(LogType.ERROR, "Entity not found, data not updated");
        }
    }
 }
