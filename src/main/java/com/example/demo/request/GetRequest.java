package com.example.demo.request;

public class GetRequest {
    private String name;
    private int dayOfTheWeek;
    private int probability;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
}
