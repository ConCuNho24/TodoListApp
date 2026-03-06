package org.example;

import java.util.ArrayList;

public class TodoListService {
    private ArrayList<TodoItem> todoItems = new ArrayList<>();

    public void addItem(String title) {
        todoItems.add(new TodoItem(title));
    }

    public ArrayList<TodoItem> getAllItems() {
        return todoItems;
    }

    public boolean updateItem(int index, String newTitle) {
        if (index >= 0 && index < todoItems.size()) {
            todoItems.get(index).setTitle(newTitle);
            return true;
        }
        return false;
    }

    public boolean deleteItem(int index) {
        if (index >= 0 && index < todoItems.size()) {
            todoItems.remove(index);
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return todoItems.isEmpty();
    }
}