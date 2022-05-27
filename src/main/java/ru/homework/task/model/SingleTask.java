package ru.homework.task.model;


public class SingleTask extends Task {
    private final boolean isRepeatable = false;

    public SingleTask() {
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    @Override
    public String toString() {
        return "SingleTask {" +
                "name='" + getName() + '\'' +
                ", categoryDict=" + getCategoryDict() +
                ", priorityDict=" + getPriorityDict() +
                ", createdDate=" + getCreatedDate() +
                ", deadLineDate=" + getDeadLineDate() +
                ", isRepeatable=" + isRepeatable +
                '}';
    }
}
