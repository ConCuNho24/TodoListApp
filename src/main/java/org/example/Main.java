package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    //>>>>>DELTE tmp users list

    //>>>>>>>>>Add todoListService
    private static TodoListService todoListService = new TodoListService();
    //<<<<<<<<<<<

    //>>>>>>>>>Change Anonymus to normal authService
    private static IAuthenticationService authService = new AuthenticationService();
    //>>>>>>>>>

    private static boolean isRunning = true;

    public static void main(String[] args) {
        while (isRunning) {
            showMenu();
        }
    }

    public static void showMenu() {
        System.out.println("Welcome to the To-Do List Application!");
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        handleMenu(choice);
    }

    public static void handleMenu(int choice) {
        switch (choice) {
            case 1:
                onLogIn();
                break;
            case 2:
                onSignUp();
                break;
            case 3:
                onExit();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public static void onLogIn() {
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = authService.logIn(username, password);

        // LIST-OPERATIONS: handle login result before showing the to-do menu
        if (user != null) {
            System.out.println("Welcome, " + user.getUsername() + "!");

            // LIST-OPERATIONS: after successful login, show CRUD menu
            showTodoMenu();

        } else {
            System.out.println("Invalid username or password!");
        }
    }

    public static void onSignUp() {
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = authService.signUp(username, password);

        // Check the result returned by signUp().
        // If a User object is returned the account was created successfully.
        if (user != null) {
            System.out.println("User " + username + " has been created successfully!");
        } else { // the username already exists and the signup fails.
            System.out.println("The username is already taken!");
        }
    }

    public static void onExit() {
        isRunning = false;
    }

    /// >>>>>>>>> ADD ShowToDo Menu:
    public static void showTodoMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\nTo-Do Menu");
            System.out.println("1. View tasks");
            System.out.println("2. Add task");
            System.out.println("3. Update task");
            System.out.println("4. Delete task");
            System.out.println("5. Log out");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        viewTasks();
                        break;
                    case 2:
                        addTask();
                        break;
                    case 3:
                        updateTask();
                        break;
                    case 4:
                        deleteTask();
                        break;
                    case 5:
                        loggedIn = false;
                        System.out.println("Logged out.");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
    /// >>>>>>>>>>>>>>>


    /// >>>>>>>>>>>> Add ViewTasks:
    public static void viewTasks() {
        if (todoListService.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        System.out.println("\nYour Tasks:");
        for (int i = 0; i < todoListService.getAllItems().size(); i++) {
            System.out.println((i + 1) + ". " + todoListService.getAllItems().get(i).getTitle());
        }
    }

    /// >>>>>>>>>>>>>>>>>>>> Add AddTask:
    public static void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        todoListService.addItem(title);
        System.out.println("Task added successfully!");
    }
    /// >>>>>>>>>>>>>>>>>>>>>


    ///  >>>>>>>>>>>>>>>>>>>>>> Add UpdateTask
    public static void updateTask() {
        viewTasks();
        if (todoListService.isEmpty()) {
            return;
        }

        System.out.print("Enter task number to update: ");
        String input = scanner.nextLine();

        try {
            int index = Integer.parseInt(input);

            System.out.print("Enter new task title: ");
            String newTitle = scanner.nextLine();

            boolean updated = todoListService.updateItem(index - 1, newTitle);

            if (updated) {
                System.out.println("Task updated successfully!");
            } else {
                System.out.println("Invalid task number!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
    /// >>>>>>>>>>>>>>>>>>>>>>>>>>

    /// >>>>>>>>>>>>>>>>>>>>>>>>> Add Delete Task:
    public static void deleteTask() {
        viewTasks();
        if (todoListService.isEmpty()) {
            return;
        }

        System.out.print("Enter task number to delete: ");
        String input = scanner.nextLine();

        try {
            int index = Integer.parseInt(input);

            boolean deleted = todoListService.deleteItem(index - 1);

            if (deleted) {
                System.out.println("Task deleted successfully!");
            } else {
                System.out.println("Invalid task number!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
    /// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}