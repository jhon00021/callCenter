package com.almundo.callcenter.model;

import org.springframework.stereotype.Component;

/**
 * Created by Jhon Velasquez
 */


@Component("Employee")
public class Employee implements Comparable<Employee> {


    private String name;

    private Level level;

    public Employee() {
    }

    public Employee(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public int compareTo(Employee employee){
        return this.level.getValue().compareTo(employee.getLevel().getValue());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
