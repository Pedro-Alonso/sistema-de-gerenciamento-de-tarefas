package SistemaGerenciamentoTarefas.src.Classes.login;

import SistemaGerenciamentoTarefas.src.Classes.user.User;

import java.util.Vector;

public class UserDatabase {

    private static UserDatabase instance = null;

    private Vector<User> users;

    private UserDatabase() {
        users = new Vector<>();
    }

    public static synchronized UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Vector<User> getUsers() {
        return users;
    }

    public void clearUsers() {
        users.clear();
    }
}
