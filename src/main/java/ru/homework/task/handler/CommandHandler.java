package ru.homework.task.handler;

import ru.homework.task.service.TaskService;
import ru.homework.task.service.TaskServiceImpl;
import ru.homework.task.service.UserService;
import ru.homework.task.service.UserServiceImpl;
import ru.homework.task.ui.UserInterface;


public class CommandHandler {
    private final TaskService taskService = new TaskServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final UserInterface userInterface = new UserInterface();

    public void listen(String command, boolean isAuthorized) throws Exception {
        if (command.equalsIgnoreCase("/help")) {
            userInterface.descriptionInterface();
        }
        if (command.equalsIgnoreCase("/show_tasks")) {
            taskService.showTasks();
        }
        if (command.equalsIgnoreCase("/create_task")) {
            taskService.createTask();
        }
        if (command.equalsIgnoreCase("/create_user")) {
            userService.createUser();
        }
        if (command.equalsIgnoreCase("/show_users") && isAuthorized) {
            userService.showUsers(isAuthorized);
        }
        if (command.equalsIgnoreCase("/exit")) {
            System.exit(0);
        }
    }
}
