package com.example.demo.request;

import com.example.demo.request.ExecuteRequest;

import java.util.Vector;

public class ResponseToARequest {
    private   String name;
    private   Vector<Double> probabilityOfArrivalOfAGivenDayOfTheWeek;
    private   Vector<String> names;
    private Vector<Vector<String>> allP = new Vector<>();
    private Double probability;
    private String dayOfWeek;
    private long personneNumber;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAllP(Vector<Vector<String>> allP){
        this.allP = allP;
    }
    public Vector<Vector<String>> getAllP(){
        return allP;
    }
    public void setProbabilityOfArrivalOfAGivenDayOfTheWeek(Vector<Double> probabilityOfArrivalOfAGivenDayOfTheWeek) {
        this.probabilityOfArrivalOfAGivenDayOfTheWeek = probabilityOfArrivalOfAGivenDayOfTheWeek;
    }

    public Vector<Double> getProbabilityOfArrivalOfAGivenDayOfTheWeek() {
        return probabilityOfArrivalOfAGivenDayOfTheWeek;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Double getProbability() {
        return probability;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void getAllDayInWeek(){
        names = new Vector<>();
        for(int i = 1; i <= 7; i++)
            names.add(ExecuteRequest.getNameOfTheDay(i));
    }

    public Vector<String> getNames() {
        return names;
    }

    public void setNames(Vector<String> names) {
        this.names = names;
    }

    public void setPersonneNumber(long personneNumber) {
        this.personneNumber = personneNumber;
    }

    public long getPersonneNumber() {
        return personneNumber;
    }
}
