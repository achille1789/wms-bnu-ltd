package entities;

/**
 * A class to create User objects.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class User extends Entity implements IUser {
    
    /**
     * Add a new entity User.
     */
    public User add(UserData user) {
        entityData.put(Data.NAME, user.getName());
        entityData.put(Data.SURNAME, user.getSurname());
        entityData.put(Data.EMAIL, user.getEmail());
        entityData.put(Data.ADDRESS, user.getAddress());
        entityData.put(Data.CREDIT_CARD, user.getCreditCard());
        return this;
    }
    
    /**
     * Add a new User order.
     */
    public void addNewOrder() {
        System.out.println("new order placed"); // TODO: remove log
    }
    
    /**
     * Getters
     */
    public String getSurname() {
        return entityData.get(Data.SURNAME);
    }
}
