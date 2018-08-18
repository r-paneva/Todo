package com.example.rumy.todosystem.models;

public class Todo {

    public Todo(String title, String description, Boolean isActive) {
        this.title = title;
        this.description = description;
        this.isActive = isActive;
    }

    public String title;
    public String description;
    public Boolean isActive;

    public Todo() {

    }

}
