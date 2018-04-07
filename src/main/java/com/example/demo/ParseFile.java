package com.example.demo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

public class ParseFile {

    private static final String directoryPath = "C:\\Users\\Женя\\Documents\\Spring\\raw data-20180331T151600Z-001\\data";
    private static  Vector<String> filePath = new Vector<>();

    public static void parseFile(String name){
        XSSFWorkbook excelBook;
        XSSFSheet excelSheet;
        XSSFRow row;

        Person person = new Person();
        person.setName(name);
        name += ".xlsx";
        findFile(directoryPath, name);

        /*Если нет такого файла, вывести сообщение*/

//i - количество файлов с одним именем(один человек - разные периоды времени)
//j - количество записей о человеке
        for(int i = 0; i < filePath.size(); i++) {
            try {
                excelBook = new XSSFWorkbook(new FileInputStream(filePath.get(i)));
                excelSheet = excelBook.getSheet("Лист1");
                int rowNumber = 9, countOfRecord = 0;
                if(i == 0) {
                    row = excelSheet.getRow(rowNumber - 3);
                    person.setPersonneNumber(Long.parseLong(row.getCell(8).getStringCellValue()));
                }
                row = excelSheet.getRow(rowNumber);
                getInfo:
                while (true) {
                    if(row.getCell(0).getCellType() == XSSFCell.CELL_TYPE_STRING){
                        String[] date = row.getCell(0).getStringCellValue().split("\\.");
                        if((date[0] + ".xlsx").compareTo(name) == 0)
                            break getInfo;
                        Calendar myCalendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));
                        SimpleDateFormat df = new SimpleDateFormat("EEEEE-dd-MM-yyyy");
                        person.setDataAboutPerson(new AboutPerson());
                        person.getDataAboutPerson().lastElement().setDate(myCalendar.getTime());

                    }

                   /* if (row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        person.getDataAboutPerson().get(j).setEntryTime(row.getCell(1).getStringCellValue());
                        person.getDataAboutPerson().get(j).setExitTime(row.getCell(2).getStringCellValue());
                    }
                    if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        person.getDataAboutPerson().get(j).setRoomNumberEntry((int) row.getCell(3).getNumericCellValue());
                        person.getDataAboutPerson().get(j).setRoomNumbertExit((int) row.getCell(4).getNumericCellValue());
                    }*/
                    rowNumber++;
                    row = excelSheet.getRow(rowNumber);
                }

                } catch(IOException e){
                     e.printStackTrace();
                }

        }

    }

    public static boolean findFile(String directoryPath, String fileName){

        File directory = new File(directoryPath);
       /* File[] listOfFiles = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".xlsx");
            }
        });*/

        File[] listOfFiles = directory.listFiles();
       if(listOfFiles == null)
           return false;

       for(File file: listOfFiles){
           if(file.getName().compareTo(fileName) == 0)
               filePath.add(file.getPath());
           if(file.isDirectory())
               findFile(directoryPath + "\\" +file.getName(), fileName);


       }
       return true;
    }
}
