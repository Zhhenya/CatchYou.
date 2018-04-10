package com.example.demo.request;

import com.example.demo.Constant;
import com.example.demo.personInfo.Person;

import java.util.Calendar;
import java.util.Vector;

public class ExecuteRequest {

    public static Vector<Double> probabilityOfArrivalOfAnEmployeeForEachDayOfTheWeek(Person person){
        Vector<Double> probabilityOfArrivalOfAGivenDayOfTheWeek = new Vector<>();
        for(int i = 0; i < Constant.NUMBER_OF_DAYS_IN_A_WEEK; i++)
            probabilityOfArrivalOfAGivenDayOfTheWeek.add(0.0);
        for(int i = 0; i < person.getDataAboutPerson().size(); i++) {
            Double time = Constant.converToMinures(person.getDataAboutPerson().get(i).getDurationOfStay())/(double)Constant.converToMinures(person.getAllTimeofWork());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(person.getDataAboutPerson().get(i).getDate());
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek == 1)
                dayOfWeek = 6;
            else
                dayOfWeek -= 2;
            probabilityOfArrivalOfAGivenDayOfTheWeek.set(dayOfWeek, probabilityOfArrivalOfAGivenDayOfTheWeek.get(dayOfWeek) + (time * 100));
        }
        return probabilityOfArrivalOfAGivenDayOfTheWeek;
    }

    public static Vector<Double> getDayForProbability(Vector<Double> probabilityOfArrivalOfAGivenDayOfTheWeek, Vector<String> nameOdTheDays, double probability){
        Vector<Double> answProbabiliry = new Vector<>();
        for(int i = 0; i < probabilityOfArrivalOfAGivenDayOfTheWeek.size(); i++){
            if(probabilityOfArrivalOfAGivenDayOfTheWeek.get(i) == probability || Math.abs(probabilityOfArrivalOfAGivenDayOfTheWeek.get(i) - probability) < 1)  {
                answProbabiliry.add(probabilityOfArrivalOfAGivenDayOfTheWeek.get(i));
                nameOdTheDays.add(getNameOfTheDay(i + 1));
            }

        }
        if(nameOdTheDays.isEmpty()) {
            nameOdTheDays.add("Не найдено");
            answProbabiliry.add(probability);
        }
        return answProbabiliry;
    }

    public static Double getProbabilityOfArrivalForOneDay(Vector<Double> probabilityOfArrivalOfAGivenDayOfTheWeek, int numberOfDay){
        return probabilityOfArrivalOfAGivenDayOfTheWeek.get(numberOfDay - 1);
    }
    public static String getNameOfTheDay(int numberOfDay){
        String nameOfTheDay = "";
        switch (numberOfDay){
            case 1: nameOfTheDay = "Понедельник"; break;
            case 2: nameOfTheDay = "Вторник"; break;
            case 3: nameOfTheDay = "Среда";break;
            case 4: nameOfTheDay = "Четверг";break;
            case 5: nameOfTheDay = "Пятница"; break;
            case 6: nameOfTheDay = "Суббота";break;
            case 7: nameOfTheDay = "Воскресенье"; break;
        }
        return nameOfTheDay;
    }


}
