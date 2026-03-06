package org.example;

import java.util.ArrayList;

public class AuthenticationService implements IAuthenticationService {

    private ArrayList<User> users;

    // Constructor
    public AuthenticationService() {
        users = new ArrayList<>();

        // default user
        users.add(new User("admin", "admin"));
    }

    @Override
    public User signUp(String username, String password) {

        // check if username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return null;
            }
        }

        // create and store new user
        User newUser = new User(username, password);
        users.add(newUser);

        return newUser;
    }

    @Override
    public User logIn(String username, String password) {

        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }
}