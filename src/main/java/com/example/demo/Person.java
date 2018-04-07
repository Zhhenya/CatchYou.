package com.example.demo;

import java.util.Vector;



public class Person {

    private String name;
    private Vector<AboutPerson> dataAboutPerson;
    private Time allTimeofWork;
    private long personneNumber;

    Person(){
        dataAboutPerson = new Vector<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Vector<AboutPerson> getDataAboutPerson() {
        return dataAboutPerson;
    }

    public void setDataAboutPerson(Vector<AboutPerson> dataAboutPerson) {
        this.dataAboutPerson = dataAboutPerson;
    }

    public Time getAllTimeofWork() {
        return allTimeofWork;
    }

    public void setAllTimeofWork(Time allTimeofWork) {
        this.allTimeofWork = allTimeofWork;
    }

    public long getPersonneNumber() {
        return personneNumber;
    }

    public void setPersonneNumber(long personneNumber) {
        this.personneNumber = personneNumber;
    }

    public void setDataAboutPerson(AboutPerson infoAboutPerson){
        dataAboutPerson.add(infoAboutPerson);
    }
}
