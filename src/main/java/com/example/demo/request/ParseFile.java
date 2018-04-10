package com.example.demo.request;

import com.example.demo.personInfo.Person;
import com.example.demo.Constant;
import com.example.demo.personInfo.AboutPerson;
import com.example.demo.personInfo.Person;
import com.example.demo.request.ExecuteRequest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ParseFile {

    private static final String directoryPath = "src\\raw data-20180331T151600Z-001\\data";
    private static  Vector<String> filePath  = new Vector<>();
    private static Person person  = new Person();
    private static Vector<Vector<String>> allPersonRequest = new Vector<>();
    private static Vector<String> allNames = new Vector<>();
    private static Vector<Integer> countPersonInDay = new Vector<>();
    private static boolean oneORmany = false;
    public static Person getPerson() {
        return person;
    }

    public static void clear(){
        filePath.clear();
        person.clear();
        person = new Person();
        oneORmany = false;
        allPersonRequest = new Vector<>();
        countPersonInDay = new Vector<>();
      /*  for(int i = 0; i < Constant.MIDNIGHT_TIME; i++)
            countPersonInDay.add(0);
*/
        allNames = new Vector<>();
    }

    public static Vector<String> getAllNames(){
        return allNames;
    }
    public static Vector<Vector<String>> getAllPersonRequest() {
        return allPersonRequest;
    }

    public static Vector<Integer> getCountPersonInDay(){
        return countPersonInDay;
    }

    public static boolean parseFileForOnePersone(String name){

        person.setName(name);
        name += ".xlsx";
        findFile(directoryPath, name);

        if(filePath.isEmpty())
            return false;


        for(int i = 0; i < filePath.size(); i++) {
           if(!parseRow(name, i))
               return false;
        }

        return true;
    }


    public static boolean parseRow(String name, int i){
       emptyFile:
        try {
            XSSFWorkbook excelBook = new XSSFWorkbook(new FileInputStream(filePath.get(i)));
            XSSFSheet excelSheet = excelBook.getSheet("Лист1");
            XSSFRow row;
            int rowNumber = 9, countOfCellInRecord = 0;
            row = excelSheet.getRow(rowNumber - 3);
            if(row == null || row.getCell(8).getStringCellValue().compareTo("") == 0 ) {
                if(filePath.size() != 1)
                    break emptyFile;
                else return false;
            }

            if(person.getPersonneNumber() == 0)
                person.setPersonneNumber(Long.parseLong(row.getCell(8).getStringCellValue()));

            row = excelSheet.getRow(rowNumber);
            getInfo:
            while (true) {
                countOfCellInRecord = 0;
                if(row.getCell(countOfCellInRecord).getCellType() == XSSFCell.CELL_TYPE_STRING){
                    String checkRowCellValue = "";
                    String[] date = row.getCell(countOfCellInRecord).getStringCellValue().split("\\.");
                    if((date[0] + ".xlsx").compareTo(name) == 0)
                        break getInfo;
                    Calendar myCalendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));
                    person.setDataAboutPerson(new AboutPerson());
                    person.getDataAboutPerson().lastElement().setDate(myCalendar.getTime());
                    countOfCellInRecord++;
                    //если нет времени выхода - добавить(время входа и наоборот)
                    if(row.getCell(countOfCellInRecord).getStringCellValue().compareTo("") == 0)
                        person.getDataAboutPerson().lastElement().setEntryTime("00:00");
                    else {
                        if(row.getCell(countOfCellInRecord).getStringCellValue().length() > 5) {
                            String[] dateTime = row.getCell(countOfCellInRecord).getStringCellValue().split(" ");
                            person.getDataAboutPerson().lastElement().setEntryTime(dateTime[1]);
                        } else
                            person.getDataAboutPerson().lastElement().setEntryTime(row.getCell(countOfCellInRecord).getStringCellValue());
                    }

                    countOfCellInRecord += 2;
                    if(row.getCell(countOfCellInRecord).getStringCellValue().compareTo("") == 0)
                        person.getDataAboutPerson().lastElement().setExitTime("00:00");
                    else {
                        if(row.getCell(countOfCellInRecord).getStringCellValue().length() > 5) {
                            String[] dateTime = row.getCell(countOfCellInRecord).getStringCellValue().split(" ");
                            person.getDataAboutPerson().lastElement().setExitTime(dateTime[1]);
                            countOfCellInRecord++;
                        }else
                            person.getDataAboutPerson().lastElement().setExitTime(row.getCell(countOfCellInRecord++).getStringCellValue());
                    }

                    if(row.getCell(countOfCellInRecord).getStringCellValue().compareTo("") != 0) {
                        String[] temp = row.getCell(countOfCellInRecord).getStringCellValue().split(" ");
                        person.getDataAboutPerson().lastElement().setRoomNumberEntry(Integer.parseInt(temp[1]));
                    }
                    countOfCellInRecord += 2;
                    if(row.getCell(countOfCellInRecord).getStringCellValue().compareTo("") != 0 ) {
                        String[] temp = row.getCell(countOfCellInRecord).getStringCellValue().split(" ");
                        person.getDataAboutPerson().lastElement().setRoomNumbertExit(Integer.parseInt(temp[1]));
                        if(person.getDataAboutPerson().lastElement().getRoomNumberEntry() == 0)
                            person.getDataAboutPerson().lastElement().setRoomNumberEntry(Integer.parseInt(temp[1]));
                    }
                    else
                        person.getDataAboutPerson().lastElement().setRoomNumbertExit(person.getDataAboutPerson().lastElement().getRoomNumberEntry());


                    countOfCellInRecord += 3;
                    //счет общего времени и промежутка рабочего времени за день
                    person.getDataAboutPerson().lastElement().calculateDuratinonOfStay();
                    person.addAllTime();
                }

                rowNumber++;
                row = excelSheet.getRow(rowNumber);
            }

        } catch(IOException e){
            return false;
        }
        return true;
    }


    public static boolean findFile(String directoryPath, String fileName){

        File directory = new File(directoryPath);
        File[] listOfFiles = directory.listFiles();

       if(listOfFiles == null)
           return false;
           for (File file : listOfFiles) {
               if (file.getName().compareTo(fileName) == 0 && !oneORmany)
                   filePath.add(file.getPath());
               else
                   if(file.getName().endsWith(".xlsx")&& oneORmany)
                       filePath.add(file.getPath());
               if (file.isDirectory())
                   findFile(directoryPath + "\\" + file.getName(), fileName);
           }

       return true;
    }

    public static boolean parseFileForAllPersons(int probability){

        oneORmany = true;
        findFile(directoryPath, "");
        if(filePath.isEmpty())
            return false;

        Collections.sort(filePath, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Paths.get(o1).getFileName().compareTo(Paths.get(o2).getFileName());
            }
        });

        Path pathPrev = Paths.get(filePath.get(0));
        Path pathNext;
        parseRow(pathPrev.getFileName().toString(), 0);

        for(int i = 1; i < filePath.size(); i++){
            pathNext = Paths.get(filePath.get(i));
            /*String name1 = pathNext.getFileName().toString();
            String name2 = pathPrev.getFileName().toString();*/
            if(pathNext.getFileName().compareTo(pathPrev.getFileName()) != 0){
                pathPrev = Paths.get(filePath.get(i));
                Vector<Double> tempProbability = new Vector<>();
                Vector<String> daysOfArrivingOnePersone = new Vector<>();
                tempProbability = ExecuteRequest.probabilityOfArrivalOfAnEmployeeForEachDayOfTheWeek(person);
                String[] getNameForPerson = pathPrev.getFileName().toString().split("\\.");
                daysOfArrivingOnePersone.add(getNameForPerson[0]);
                ExecuteRequest.getDayForProbability(tempProbability, daysOfArrivingOnePersone, (double)probability);
                if(daysOfArrivingOnePersone.size() >= 2) {
                 //   allNames.add(getNameForPerson[0]);
                    allPersonRequest.add(daysOfArrivingOnePersone);
                   // countPerson();
                }
                person = new Person();
            }
            parseRow(pathNext.getFileName().toString(), i);
        }

        if(allPersonRequest.size() == 0)
            return false;

        return true;
    }

   /* public static void countPerson(){
            for(int i = 0; i < person.getDataAboutPerson().size(); i++) {

                int a = person.getDataAboutPerson().get(i).getEntryTime().get(0);
                int b = person.getDataAboutPerson().get(i).getExitTime().get(0);
                if(person.getDataAboutPerson().get(i).getEntryTime().get(0) < person.getDataAboutPerson().get(i).getExitTime().get(0)){
                    for (int j = a; j < b; j++)
                        countPersonInDay.set(j, countPersonInDay.get(j) + 1);
                }
                if(person.getDataAboutPerson().get(i).getEntryTime().get(0) > person.getDataAboutPerson().get(i).getExitTime().get(0)){
                    for (int j = a; j < Constant.MIDNIGHT_TIME; j++)
                        countPersonInDay.set(j, countPersonInDay.get(j) + 1);
                    for(int j = 0; j < b; j++)
                        countPersonInDay.set(j, countPersonInDay.get(j) + 1);
                }

            }
    }*/

}
