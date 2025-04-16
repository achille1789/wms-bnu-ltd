import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import entities.User;
import entities.UserData;
import entities.Data;

/**
 * A class that handles the list of Users.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
 class Users {
    // The fields.
    private List<User> usersList = new LinkedList<>();
    
    // TODO: handle wrong input data
    /**
     * Set all the user data to create a new user.
     * If data is invalid print an error message.
     */
    public void addUser(String name, String surname, String email, String address, String creditCard) {
        UserData data = new UserData(name, surname, email, address, creditCard);
        boolean exists = false;
        for (int i = 0; i < usersList.size(); i++) {
            if (name.equals(usersList.get(i).getName())) {
                Logger.error("User already exists, aborted");
                exists = true;
                break;
            }
        }
        if (!exists) {
            usersList.add(new User().add(data));
            Logger.info("User added, data: " + usersList.getLast().getAllData().toString());
        }
    }
    
    /**
     * Get the number of users in the list.
     */
    public int getTotalUsers() {
        return usersList.size();
    }
    
    /**
     * Get the name of all users in the list.
     */
    public String[] getUserNames() {
        String[] names = new String[usersList.size()];
        for (int i = 0; i < usersList.size(); i++) {
            names[i] = usersList.get(i).getName();
        }
        return names;
    }
    
    /**
     * Get data of the passed User.
     */
    public HashMap<Data, String> getUserData(String name) {
        HashMap<Data, String> userData = null;
        for (int i = 0; i < usersList.size(); i++) {
            if (name.equals(usersList.get(i).getName())) {
                userData = usersList.get(i).getAllData();
            }
        }
        return userData;
    }
    
    /**
     * Update data of the passed User.
     */
    public void updateUserData(String name, Data key, String value) {
        boolean found = false;
        for (int i = 0; i < usersList.size(); i++) {
            if (name.equals(usersList.get(i).getName())) {
                usersList.get(i).update(key, value);
                Logger.info("User data updated: " + usersList.get(i).getAllData().toString());
                found = true;
            }
        }
        if (!found) {
            Logger.error("User not found, data not updated");
        }
    }
    
    /**
     * Delete passed User.
     */
    public void deleteUser(String name) {
        boolean found = false;
        for (int i = 0; i < usersList.size(); i++) {
            if (name.equals(usersList.get(i).getName())) {
                usersList.remove(i);
                Logger.info("User deleted");
                found = true;
            }
        }
        if (!found) {
            Logger.error("User not found, data not updated");
        }
    }
 }
