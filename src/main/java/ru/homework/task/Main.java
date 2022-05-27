package ru.homework.task;

import ru.homework.task.handler.CommandHandler;
import ru.homework.task.service.TaskService;
import ru.homework.task.service.TaskServiceImpl;
import ru.homework.task.service.UserService;
import ru.homework.task.service.UserServiceImpl;
import ru.homework.task.ui.UserInterface;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;


public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());
    private static final UserService userService = new UserServiceImpl();
    private static final TaskService taskService = new TaskServiceImpl();
    private static final int wrongLogPassCount = 3;
    private static int firstTry = 1;

    public static void main(String[] args) throws Exception {
        setUpApplication();

        boolean isAuthorized = false;

        new UserInterface().appNameInterface();

        while (firstTry <= wrongLogPassCount) {
            if (login(isAuthorized)) {
                isAuthorized = true;
                System.out.println("Успешная авторизация!");
                break;
            } else {
                System.out.println("Неверная пара: логин + пароль!");
                firstTry++;
            }
        }

        if (!isAuthorized) {
            System.exit(0);
        }


        while (true) {
            new UserInterface().enterCommandInterface();
            Scanner scanner = new Scanner(System.in);
            String userString = scanner.nextLine();

            new CommandHandler().listen(userString, isAuthorized);
        }
    }

    private static void setUpApplication() throws IOException {
        log.info("Выполняется настройка приложения...");
        userService.setUp();
        taskService.setUp();
        log.info("Настройка приложения завершена. Приятного пользования!");
    }

    private static boolean login(boolean isAuthorized) throws IOException {
        System.out.println("Введите логин: \n>");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();
        System.out.println("Введите пароль: \n>");
        String password = scanner.nextLine();
        return userService.login(login, password, isAuthorized);
    }
}
