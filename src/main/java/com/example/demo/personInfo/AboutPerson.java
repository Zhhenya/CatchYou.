package com.example.demo.personInfo;

import com.example.demo.Constant;
import org.omg.CORBA.INTERNAL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

//@Entity

public class AboutPerson {
  //  @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private Vector<Integer> entryTime;
    private Vector<Integer> exitTime;
    private int roomNumberEntry;
    private int roomNumbertExit;
    private Vector<Integer> durationOfStay;

    public AboutPerson(){
        entryTime = new Vector<>();
        entryTime.add(0);
        entryTime.add(0);
        exitTime = new Vector<>();
        exitTime.add(0);
        exitTime.add(0);
        durationOfStay = new Vector<>();
        durationOfStay.add(0);
        durationOfStay.add(0);
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Vector<Integer> getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String time) {
        String[] temp =  time.split(":");
        entryTime.set(0, Integer.parseInt(temp[0]));
        entryTime.set(1, Integer.parseInt(temp[1]));
    }

    public Vector<Integer> getExitTime() {
        return exitTime;
    }

    public void setExitTime(String time) {
        String[] temp =  time.split(":");
        exitTime.set(0, Integer.parseInt(temp[0]));
        exitTime.set(1, Integer.parseInt(temp[1]));
    }

    public int getRoomNumberEntry() {
        return roomNumberEntry;
    }

    public void setRoomNumberEntry(int roomNumberEntry) {
        this.roomNumberEntry = roomNumberEntry;
    }

    public int getRoomNumbertExit() {
        return roomNumbertExit;
    }

    public void setRoomNumbertExit(int roomNumbertExit) {
        this.roomNumbertExit = roomNumbertExit;
    }

    public Vector<Integer> getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(Vector<Integer> durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    public Vector<Integer> difTime(Vector<Integer> time1, Vector<Integer> time2){
        Vector<Integer> resultTime = new Vector<>();
        if(time2.get(0) >= time1.get(0) ){
            if(time2.get(1) < time1.get(1)) {
                resultTime.add((time2.get(0) - 1) - time1.get(0));
                resultTime.add(time2.get(1) + Constant.ALL_MINUTE - time1.get(1));
            }
            if(time2.get(1) > time1.get(1)){
                resultTime.add(time2.get(0) - time1.get(0));
                resultTime.add(time2.get(1) - time1.get(1));
            }
        }
        if(time2.get(0) < time1.get(0)){
            if(time2.get(1) > time1.get(1)){
                resultTime.add((Constant.NOON_TIME - time1.get(0)) + Constant.NOON_TIME + time2.get(0));
                resultTime.add(time2.get(1) - time1.get(1));
            }
            if(time2.get(1) < time1.get(1)){
                resultTime.add((Constant.NOON_TIME - time1.get(0)) + Constant.NOON_TIME + time2.get(0) - 1);
                resultTime.add(time2.get(1) + Constant.ALL_MINUTE - time1.get(1));
            }
        }
        return resultTime;
    }

    public void calculateDuratinonOfStay(){

        if(exitTime.get(0) >= entryTime.get(0)){
            if(exitTime.get(1) > entryTime.get(1)) {
                durationOfStay.set(0, exitTime.get(0) - entryTime.get(0));
                durationOfStay.set(1, exitTime.get(1) - entryTime.get(1));
                return;
            }
            durationOfStay.set(0, (exitTime.get(0) - 1) - entryTime.get(0));
            durationOfStay.set(1, (exitTime.get(1) + Constant.ALL_MINUTE) - entryTime.get(1));
            return;
        }
        if((entryTime.get(0) < Constant.NOON_TIME || entryTime.get(0) == Constant.NOON_TIME&& entryTime.get(1) == 0 )&& exitTime.get(0) < Constant.NOON_TIME) {
            Vector<Integer> resultTime = difTime(entryTime, exitTime);
            durationOfStay.set(0, resultTime.get(0));
            if(resultTime.get(1) > Constant.MAX_MINUTE) {
                durationOfStay.set(0, resultTime.get(0) + resultTime.get(1) / Constant.ALL_MINUTE);
                durationOfStay.set(1, resultTime.get(1) % Constant.ALL_MINUTE);
            }else
                durationOfStay.set(1, resultTime.get(1));
            return;
        }
        if(entryTime.get(0) > exitTime.get(0)){

        }


    }
}
