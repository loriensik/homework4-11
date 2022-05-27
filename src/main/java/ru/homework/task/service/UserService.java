package ru.homework.task.service;

import ru.homework.task.model.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void setUp() throws IOException;

    boolean login(String login, String password, boolean isAuthorized) throws IOException;

    void createUser();

    List<User> showUsers(boolean isAuthorized) throws IOException;
}
