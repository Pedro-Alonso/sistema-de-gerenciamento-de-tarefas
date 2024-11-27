package Classes.Controller;

import java.util.Observable;

import Classes.DTO.LoggerRecordDto;
import Classes.Model.User;
import Classes.Model.UserSession;

@SuppressWarnings("deprecation")
public class UserController extends Observable {
    /**
     * Constructor for the UserController class.
     */
    public UserController() {
        super();
    }

    /**
     * Method to notify observers of a change.
     */
    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }

    /**
     * Method to create a user.
     * @param name The name of the user -> {@link String}
     * @param email The email of the user -> {@link String}
     * @param password The password of the user -> {@link String}
     * @return The created user -> {@link User}
     */
    public User createUser(String name, String email, String password) {
        User user = new User(name, email, password);
        LoggerRecordDto log = new LoggerRecordDto(user, user, "User created.");
        setChanged();
        notifyObservers(log);
        return user;
    }

    /**
     * Method to update the name of a user.
     * @param userSession The session of the user to be updated -> {@link UserSession}
     * @param name The new name for the user -> {@link String}
     */
    public void updateUserName(UserSession userSession, String name) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        User user = userSession.getUser();
        if (user == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        String oldName = user.getUsername();
        user.setUsername(name);
        LoggerRecordDto log = new LoggerRecordDto(user, user, "User name updated from '" + oldName + "' to '" + name + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the email of a user.
     * @param userSession The session of the user to be updated -> {@link UserSession}
     * @param email The new email for the user -> {@link String}
     */
    public void updateUserEmail(UserSession userSession, String email) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        User user = userSession.getUser();
        if (user == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        String oldEmail = user.getUserEmail();
        user.setUserEmail(email);
        LoggerRecordDto log = new LoggerRecordDto(user, user, "User email updated from '" + oldEmail + "' to '" + email + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the password of a user.
     * @param userSession The session of the user to be updated -> {@link UserSession}
     * @param password The new password for the user -> {@link String}
     */
    public void updateUserPassword(UserSession userSession, String password) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        User user = userSession.getUser();
        if (user == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        user.setUserPassword(password);
        LoggerRecordDto log = new LoggerRecordDto(user, user, "User password updated.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a user.
     * @param userSession The session of the user to be removed -> {@link UserSession}
     */
    public void removeUser(UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        User user = userSession.getUser();
        if (user == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        LoggerRecordDto log = new LoggerRecordDto(user, user, "User removed.");
        setChanged();
        notifyObservers(log);
    }
}