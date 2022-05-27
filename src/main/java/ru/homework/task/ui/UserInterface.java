package ru.homework.task.ui;

public class UserInterface {
    public void appNameInterface() {
        String info =
                "XXXXXXXXXXXXXXXXXXXXXXXXXX" +
                "\n     Планировщик задач" +
                "\nXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                "\nВведите: /help для помощи" +
                "\nXXXXXXXXXXXXXXXXXXXXXXXXXX";

        System.out.println(info);
    }

    public void descriptionInterface() {
        String info = "Команды:" +
                "\nПомощь: /help" +
                "\nПоказать задачи: /show_tasks" +
                "\nСоздать задачу: /create_task" +
                "\nПоказать пользователей: /show_users" +
                "\nСоздать пользователя: /create_user" +
                "\nВыйти из программы: /exit";

        System.out.println(info);
    }

    public void enterCommandInterface() {
        String info = "\nВведите команду:" +
                "\n>";

        System.out.println(info);
    }
}
