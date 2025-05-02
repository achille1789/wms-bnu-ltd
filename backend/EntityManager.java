package backend;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import backend.entities.Entity;
import backend.entities.Data;
import utils.*;

/**
 * A class that handles the list of Entities.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public abstract class EntityManager {
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
     * Get list index of entity that matches the passed Id.
     * @param id of the entity
     * @return the index of the entity
     */
     private int getEntityIndex(String id) {
        int index = -1;
        for (int i = 0; i < this.entitiesList.size(); i++) {
            if (id.equals(this.entitiesList.get(i).getId())) {
                index = i;
                break;
            }
        }
        return index;
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
     * Get data of the passed id Entity.
     * @param id of the entity
     * @return a HashMap with all the entity data
     */
    public HashMap<Data, String> getEntityData(String id) {
        int index = getEntityIndex(id);
        if (index > -1) {
            return this.entitiesList.get(index).getAllData();
        }
        printlog(LogType.ERROR, this.logEntityType + " not found");
        return null;
    }

    /**
     * Update data of the passed Entities.
     * @param id the id of the entity
     * @param newData a HashMap with the data to update
     */
    public void updateEntityData(String id, HashMap<Data, String> newData) {
        int index = getEntityIndex(id);
        if (index > -1) {
            for (Data key : newData.keySet()) {
              this.entitiesList.get(index).update(key, newData.get(key));
            }
            printlog(LogType.INFO, this.logEntityType + " data updated: " + this.entitiesList.get(index).getAllData().toString());
        } else {
            printlog(LogType.ERROR, this.logEntityType + " not found, data not updated");
        }
    }

    /**
     * Delete passed Entities.
     * @param id the id of the entity
     */
    public void deleteEntity(String id) {
        int index = getEntityIndex(id);
        if (index > -1) {
            this.entitiesList.remove(index);
            printlog(LogType.INFO, this.logEntityType + " deleted");
        } else {
            printlog(LogType.ERROR, this.logEntityType + " not found, entity not deleted");
        }
    }
    
    /**
     * Get Entity based on the passed id.
     * @param id the id of the entity
     * @return the entity
     */
    public Entity getEntityById(String id) {
        int index = getEntityIndex(id);
        if (index > -1) {
            return this.entitiesList.get(index);
        }
        printlog(LogType.ERROR, this.logEntityType + " not found");
        return null;
    }
 }
