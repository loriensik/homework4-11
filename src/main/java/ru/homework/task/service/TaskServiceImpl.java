package ru.homework.task.service;

import ru.homework.task.constants.CategoryDict;
import ru.homework.task.constants.PriorityDict;
import ru.homework.task.model.RepeatableTask;
import ru.homework.task.model.SingleTask;
import ru.homework.task.model.Task;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

public class TaskServiceImpl implements TaskService {
    private final Logger log = Logger.getLogger(TaskServiceImpl.class.getName());
    private final String TASK_FILE_PATH = "src/main/resources/tasks.txt";

    @Override
    public void setUp() throws IOException {
        prepareTaskFile();
    }

    @Override
    public void createTask() {
        String stringBuilder = "Выберите тип задачи: \n" +
                "1 - одноразовая задача \n" +
                "2 - многоразовая задача \n" +
                ">";
        System.out.println(stringBuilder);
        Scanner scanner = new Scanner(System.in);
        String taskType = scanner.nextLine();
        if (taskType.equals("1")) {
            mapTask(new SingleTask(), scanner);
        } else if (taskType.equals("2")) {
            mapTask(new RepeatableTask(), scanner);
        }
    }

    @Override
    public void showTasks() throws IOException {
        readTaskFile();
    }

    private <T extends Task> Task mapTask(T task, Scanner scanner) {
        System.out.println("Введите название задачи:");
        String name = scanner.nextLine();
        task.setName(name);

        System.out.println("Введите категорию задачи: \n" + Arrays.toString(CategoryDict.values())
        );
        String categoryDict = scanner.nextLine();
        task.setCategoryDict(CategoryDict.valueOf(categoryDict));

        System.out.println("Введите приоритет задачи: \n" + Arrays.toString(PriorityDict.values()));
        String priorityDict = scanner.nextLine();
        task.setPriorityDict(PriorityDict.valueOf(priorityDict));

        task.setCreatedDate(LocalDate.now());
        LocalDate createdDate = task.getCreatedDate();
        System.out.println("Дата создания задачи: " + createdDate);

        System.out.println("Введите количество дней на выполнение задачи:");
        String deadLineDate = scanner.nextLine();
        task.setDeadLineDate(createdDate.plusDays(Integer.parseInt(deadLineDate)));

        System.out.println(task);

        try(FileWriter fileWriter = new FileWriter(TASK_FILE_PATH, true)) {

            StringBuilder taskLine = new StringBuilder();
            taskLine.append(task.getName())
                    .append(" ")
                    .append(task.getCategoryDict())
                    .append(" ")
                    .append(task.getPriorityDict())
                    .append(" ")
                    .append(task.getCreatedDate())
                    .append(" ")
                    .append(task.getDeadLineDate())
                    .append(" ");

            if (task instanceof SingleTask) {
                taskLine.append(false);
            } else {
                taskLine.append(true);
            }

            taskLine.append("\n");

            fileWriter.write(taskLine.toString());
            fileWriter.flush();
            System.out.println("Задача успешно создана! \n>");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return task;
    }

    private void prepareTaskFile() throws IOException {
        log.info("Создаю файл с задачами...");
        File file = new File(TASK_FILE_PATH);
        if (file.createNewFile()) {
            log.info("Файл с задачами успешно создан!");
        } else {
            log.warning("Файл с задачами уже существует!");
        }
    }

    private void readTaskFile() throws IOException {
        FileReader fileReader = new FileReader(TASK_FILE_PATH);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        while (line != null) {
            String[] taskLine = line.split(" ");

            if (taskLine[5] != null && taskLine[5].equals("true")) {
                RepeatableTask repeatableTask = new RepeatableTask();
                repeatableTask.setName(taskLine[0]);
                repeatableTask.setCategoryDict(CategoryDict.valueOf(taskLine[1]));
                repeatableTask.setPriorityDict(PriorityDict.valueOf(taskLine[2]));
                repeatableTask.setCreatedDate(LocalDate.parse(taskLine[3]));
                repeatableTask.setDeadLineDate(LocalDate.parse(taskLine[4]));
                System.out.println(repeatableTask);
            } else {
                SingleTask singleTask = new SingleTask();
                singleTask.setName(taskLine[0]);
                singleTask.setCategoryDict(CategoryDict.valueOf(taskLine[1]));
                singleTask.setPriorityDict(PriorityDict.valueOf(taskLine[2]));
                singleTask.setCreatedDate(LocalDate.parse(taskLine[3]));
                singleTask.setDeadLineDate(LocalDate.parse(taskLine[4]));
                System.out.println(singleTask);
            }

            line = bufferedReader.readLine();
        }
        fileReader.close();
        bufferedReader.close();
    }
}
