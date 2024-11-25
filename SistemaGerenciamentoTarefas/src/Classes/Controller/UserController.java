
package Classes.Controller;

import java.util.ArrayList;
import java.util.Observable;

import Classes.Project;
import Classes.Task;
import Classes.User;
import Classes.UserTask;
import Classes.DTO.LoggerRecordDto;

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
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Project> projects = new ArrayList<Project>();
        UserTask user = new UserTask(name, email, password, tasks, projects);
        LoggerRecordDto log = new LoggerRecordDto(user, user, "User created.");
        setChanged();
        notifyObservers(log);
        return user;
    }

    /**
     * Method to update the name of a user.
     * @param user The user to be updated -> {@link UserTask}
     * @param name The new name for the user -> {@link String}
     */
    public void updateUserName(UserTask user, String name) {
        String oldName = user.getUserName();
        user.setUserName(name);
        LoggerRecordDto log = new LoggerRecordDto(user, user, "User name updated from '" + oldName + "' to '" + name + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the email of a user.
     * @param user The user to be updated -> {@link UserTask}
     * @param email The new email for the user -> {@link String}
     */
    public void updateUserEmail(UserTask user, String email) {
        String oldEmail = user.getEmail();
        user.setEmail(email);
        LoggerRecordDto log = new LoggerRecordDto(user, user, "User email updated from '" + oldEmail + "' to '" + email + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the password of a user.
     * @param user The user to be updated -> {@link UserTask}
     * @param password The new password for the user -> {@link String}
     */
    public void updateUserPassword(UserTask user, String password) {
        user.setPassword(password);
        LoggerRecordDto log = new LoggerRecordDto(user, user, "User password updated.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a user.
     * @param user The user to be removed -> {@link User}
     */
    public void removeUser(UserTask user) {
        LoggerRecordDto log = new LoggerRecordDto(user, user, "User removed.");
        setChanged();
        notifyObservers(log);
    }
}