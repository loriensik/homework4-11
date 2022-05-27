package ru.homework.task.service;

import ru.homework.task.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
    private final String USER_FILE_PATH = "src/main/resources/users.txt";

    @Override
    public void setUp() throws IOException {
        prepareUserFile();
    }

    @Override
    public boolean login(String login, String password, boolean isAuthorized) throws IOException {
        User requestUser = new User();
        requestUser.setLogin(login);
        requestUser.setPassword(password);

        List<User> users = showUsers(isAuthorized);
        return users.stream()
                .anyMatch(user -> users.contains(requestUser));
    }

    @Override
    public void createUser() {
        try(FileWriter fileWriter = new FileWriter(USER_FILE_PATH, true)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите логин нового пользователя: \n>");
            String login = scanner.nextLine();
            System.out.println("Введите пароль нового пользователя: \n>");
            String password = scanner.nextLine();
            String loginPasswordLine = login + " " + password + "\n";
            fileWriter.write(loginPasswordLine);
            fileWriter.flush();
            System.out.println("Пользователь успешно создан! \n>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> showUsers(boolean isAuthorized) throws IOException {
        return readUserFile(isAuthorized);
    }

    private void prepareUserFile() throws IOException {
        log.info("Создаю файл с пользователями...");
        File file = new File(USER_FILE_PATH);
        if (file.createNewFile()) {
            log.info("Файл с пользователями успешно создан!");
        } else {
            log.warning("Файл с пользователями уже существует!");
        }
    }

    private List<User> readUserFile(boolean isAuthorized) throws IOException {
        List<User> users = new ArrayList<>();

        FileReader fileReader = new FileReader(USER_FILE_PATH);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        while (line != null) {
            String[] loginPasswordLine = line.split(" ");
            User user = new User();
            user.setLogin(loginPasswordLine[0]);

            String original = loginPasswordLine[1];
            String faked = "******";

            user.setPassword(faked);

            if (isAuthorized) {
                System.out.println(user);
            }

            user.setPassword(original);

            users.add(user);

            line = bufferedReader.readLine();
        }
        fileReader.close();
        bufferedReader.close();

        return users;
    }
}
