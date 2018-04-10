package com.example.demo.personInfo;

import com.example.demo.Constant;
import com.example.demo.personInfo.AboutPerson;

import java.util.Vector;



public class Person {

    private String name;
    private Vector<AboutPerson> dataAboutPerson;
    private Vector<Integer> allTimeofWork;
    private long personneNumber;

    public Person(){
        dataAboutPerson = new Vector<>();
        allTimeofWork = new Vector<>();
        allTimeofWork.add(0);
        allTimeofWork.add(0);
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

    public Vector<Integer> getAllTimeofWork() {
        return allTimeofWork;
    }

    public void setAllTimeofWork(Vector allTimeofWork) {
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



    public void addAllTime(){
        allTimeofWork.set(0, allTimeofWork.get(0) + dataAboutPerson.lastElement().getDurationOfStay().get(0));
        int countMinute  = allTimeofWork.get(1) + dataAboutPerson.lastElement().getDurationOfStay().get(1);
        if(countMinute > Constant.MAX_MINUTE){
            allTimeofWork.set(0, allTimeofWork.get(0) + countMinute / Constant.ALL_MINUTE);
            allTimeofWork.set(1, countMinute % Constant.ALL_MINUTE);
        }else
            allTimeofWork.set(1, countMinute);
    }
    public void clear(){
        name = "";
        dataAboutPerson.clear();
        allTimeofWork.clear();
        personneNumber = 0;
    }
}
