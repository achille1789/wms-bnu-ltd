package backend.entities;

 /**
  * An abstract class to specify needed data for a generic Entity.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
abstract class EntityData {
    // The fields.
    protected String name;
    protected String email;
    protected String address;
    
    // TODO: implement input validity checks
    /**
     * Set the default data for all entities.
     * @param name the name of the entity
     * @param email the email of the entity
     * @param address the address of the entity
     */
    protected EntityData(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
    
    /**
     * Getters
     */
    public String getName() {
        return this.name;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getAddress() {
        return this.address;
    }
}
