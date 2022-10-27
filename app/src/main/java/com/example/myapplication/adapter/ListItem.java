package com.example.myapplication.adapter;

import java.io.Serializable;

public class ListItem implements Serializable {
    //private String tableName; //тестую, чи можна міняти і діставати назву таблиці
    private String title;
    private String desc;
    private String uri = "empty";
    private String workHours;
    private String startHours;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getStartHours() {
        return startHours;
    }

    public void setStartHours(String startHours) {
        this.startHours = startHours;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
