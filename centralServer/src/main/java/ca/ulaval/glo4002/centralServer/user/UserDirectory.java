package ca.ulaval.glo4002.centralServer.user;

import java.util.ArrayList;
import java.util.List;

public class UserDirectory {

    private List<User> userList = new ArrayList<User>();
    private int lastIdGenerated = 0;

    public boolean userExists(int userID) {
        for (User user : userList) {
            if (user.getID() == userID) {
                return true;
            }
        }
        return false;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public User obtainUser(int userID) throws UserNotFoundException {
        for (User user : userList) {
            if (user.getID() == userID) {
                return user;
            }
        }
        throw new UserNotFoundException("This ID was not found in the UserDirectory "
                                        + "when trying to obtain the corresponding user");
    }

    public int generateNewID() {
        lastIdGenerated++;
        return lastIdGenerated;
    }

}
