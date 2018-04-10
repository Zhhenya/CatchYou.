package com.example.demo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Vector;

public class Constant {
    public static  final Integer ALL_MINUTE = 60;
    public static final Integer MAX_MINUTE = 59;
    public static final Integer NOON_TIME = 12;
    public static final Integer NOON_TIME_WITH_MINUTES = 11;
    public static final Integer MIDNIGHT_TIME = 24;
    public static final Integer MIDNIGHT_TIME_WITH_MINUTE = 23;
    public static final Double NUMBER_OF_MINUTES_IN_ONE_HOURS = 1440.0;
    public static final Integer NUMBER_OF_DAYS_IN_A_WEEK = 7;
    public static final Vector<Double> hours = new Vector<>();
    public static void getHours(){
        for(double i = 0; i < MIDNIGHT_TIME; i++)
            hours.add(i);
    }

    public static Integer converToMinures(Vector<Integer> time){
        return time.get(0) * 60 + time.get(1);
    }

}
