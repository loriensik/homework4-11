package ru.homework.task.model;

import ru.homework.task.constants.CategoryDict;
import ru.homework.task.constants.PriorityDict;

import java.time.LocalDate;

public abstract class Task {
    private String name;
    private CategoryDict categoryDict;
    private PriorityDict priorityDict;
    private LocalDate createdDate;
    private LocalDate deadLineDate;

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDict getCategoryDict() {
        return categoryDict;
    }

    public void setCategoryDict(CategoryDict categoryDict) {
        this.categoryDict = categoryDict;
    }

    public PriorityDict getPriorityDict() {
        return priorityDict;
    }

    public void setPriorityDict(PriorityDict priorityDict) {
        this.priorityDict = priorityDict;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(LocalDate deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", categoryDict=" + categoryDict +
                ", priorityDict=" + priorityDict +
                ", createdDate=" + createdDate +
                ", deadLineDate=" + deadLineDate +
                '}';
    }
}
