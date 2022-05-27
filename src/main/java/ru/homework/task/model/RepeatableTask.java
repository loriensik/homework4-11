package ru.homework.task.model;


public class RepeatableTask extends Task {
    private final boolean isRepeatable = true;

    public RepeatableTask() {
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    @Override
    public String toString() {
        return "RepeatableTask {" +
                "name='" + getName() + '\'' +
                ", categoryDict=" + getCategoryDict() +
                ", priorityDict=" + getPriorityDict() +
                ", createdDate=" + getCreatedDate() +
                ", deadLineDate=" + getDeadLineDate() +
                ", isRepeatable=" + isRepeatable +
                '}';
    }
}
