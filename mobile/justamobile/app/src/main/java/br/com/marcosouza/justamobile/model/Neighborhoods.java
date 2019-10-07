package br.com.marcosouza.justamobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Neighborhoods {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("days")
    @Expose
    private String days;
    @SerializedName("schedule")
    @Expose
    private String schedule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

}


