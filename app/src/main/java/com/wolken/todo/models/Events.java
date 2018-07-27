package com.wolken.todo.models;

public class Events {
    private String taskName001;
    private String taskDescription001;
    private String taskDate001;

    public Events(String taskName001, String taskDescription001, String taskDate001) {
        this.taskName001 = taskName001;
        this.taskDescription001 = taskDescription001;
        this.taskDate001 = taskDate001;
    }

    public String getTaskName001() {
        return taskName001;
    }

    public void setTaskName001(String taskName001) {
        this.taskName001 = taskName001;
    }

    public String getTaskDescription001() {
        return taskDescription001;
    }

    public void setTaskDescription001(String taskDescription001) {
        this.taskDescription001 = taskDescription001;
    }

    public String getTaskDate001() {
        return taskDate001;
    }

    public void setTaskDate001(String taskDate001) {
        this.taskDate001 = taskDate001;
    }
}
