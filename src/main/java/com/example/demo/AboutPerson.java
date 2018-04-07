package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Entity

public class AboutPerson {
  //  @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String entryTime;
    private String exitTime;
    private int roomNumberEntry;
    private int roomNumbertExit;
    private String durationOfStay;

    AboutPerson(){}

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

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTtime) {
        this.entryTime = entryTtime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
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

    public String getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(String durationOfStay) {
        this.durationOfStay = durationOfStay;
    }
}
