package entities;

/**
 * A class to create User objects.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
class User extends Entity implements IUser {
    
    public void add(UserData user) {
        entityData.put(Data.NAME, user.getName());
        entityData.put(Data.SURNAME, user.getSurname());
        entityData.put(Data.EMAIL, user.getEmail());
        entityData.put(Data.ADDRESS, user.getAddress());
        entityData.put(Data.CREDIT_CARD, user.getCreditCard());
        System.out.println("User data: " + entityData);
    }
    
    public void addNewOrder() {
        System.out.println("new order placed");
    }
}
