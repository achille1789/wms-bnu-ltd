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
    private List<Entity> entitiesList = new LinkedList<>();
    private String logEntityType = "Entity";
    
    /**
     * Get the list of entities.
     * @param type of log.
     * @param message to log.
     */
    private void printlog(LogType type, String message) {
        switch (type) {
            case LogType.ERROR:
                Logger.error(message);
                break;
            case LogType.WARN:
                Logger.warn(message);
                break;
            case LogType.INFO:
            default:
                Logger.info(message);
        }
    }
    
    
    /**
     * Setters.
     */
     protected void setLogEntityType(String logEntityType) {
        this.logEntityType = logEntityType;
     }

    /**
     * Getters.
     */
     public List<Entity> getEntitiesList() {
        return this.entitiesList;
     }
     
    /**
     * Get data of the passed Entities.
     * @param name the name of the entity
     * @param surname the surname of the entity
     * @return a HashMap with all the entity data
     */
    public HashMap<Data, String> getEntityData(String id) {
        HashMap<Data, String> entityData = null;
        for (int i = 0; i < this.entitiesList.size(); i++) {
            if (id.equals(this.entitiesList.get(i).getId())) {
                entityData = this.entitiesList.get(i).getAllData();
                break;
            }
        }
        if (entityData == null) {
            printlog(LogType.ERROR, this.logEntityType + " not found");
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
        for (int i = 0; i < this.entitiesList.size(); i++) {
            if (id.equals(this.entitiesList.get(i).getId())) {
                this.entitiesList.get(i).update(key, value);
                printlog(LogType.INFO, this.logEntityType + " data updated: " + this.entitiesList.get(i).getAllData().toString());
                found = true;
                break;
            }
        }
        if (!found) {
            printlog(LogType.ERROR, this.logEntityType + " not found, data not updated");
        }
    }

    /**
     * Delete passed Entities.
     * @param id the id of the entity
     */
    public void deleteEntity(String id) {
        boolean found = false;
        for (int i = 0; i < this.entitiesList.size(); i++) {
            if (id.equals(this.entitiesList.get(i).getId())) {
                this.entitiesList.remove(i);
                printlog(LogType.INFO, this.logEntityType + " deleted");
                found = true;
                break;
            }
        }
        if (!found) {
            printlog(LogType.ERROR, this.logEntityType + " not found, data not updated");
        }
    }
 }
