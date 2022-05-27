package ru.homework.task.service;

import ru.homework.task.exception.TypeNotFoundException;

import java.io.IOException;

public interface TaskService {
    void createTask() throws TypeNotFoundException, IOException;

    void setUp() throws IOException;

    void showTasks() throws IOException;
}
