package com.example.demo.Controllers;

import com.example.demo.request.ExecuteRequest;
import com.example.demo.request.GetRequest;
import com.example.demo.request.ParseFile;
import com.example.demo.request.ResponseToARequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CatchPersonInOneDayController {

    @RequestMapping(value = "/catchPersonInOneDay", method = RequestMethod.POST)
    public String catchDay(Model model){
        model.addAttribute("request", new GetRequest() );
        return "catchPersonInOneDay";
    }

    @RequestMapping(value = "/afterCatch", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String whenCatchDay(Model model, GetRequest getRequest){
        ResponseToARequest responseToARequest = new ResponseToARequest();
        ParseFile.clear();
        model.addAttribute("exception", "");
        if(getRequest.getName() != "" && getRequest.getDayOfTheWeek() >= 1 && getRequest.getDayOfTheWeek() <= 7) {
              if(ParseFile.parseFileForOnePersone(getRequest.getName()) != false) {
                  responseToARequest.setProbabilityOfArrivalOfAGivenDayOfTheWeek(ExecuteRequest.probabilityOfArrivalOfAnEmployeeForEachDayOfTheWeek(ParseFile.getPerson()));
                  responseToARequest.setName(getRequest.getName());
                  responseToARequest.setProbability(ExecuteRequest.getProbabilityOfArrivalForOneDay(responseToARequest.getProbabilityOfArrivalOfAGivenDayOfTheWeek(), getRequest.getDayOfTheWeek()));
                  responseToARequest.setDayOfWeek(ExecuteRequest.getNameOfTheDay(getRequest.getDayOfTheWeek()));
                  responseToARequest.setPersonneNumber(ParseFile.getPerson().getPersonneNumber());
              }
              else
                  model.addAttribute("exception", "Файл пуст, либо не существует");

        }
        else
            model.addAttribute("exception", "Неверный формат входных данных");
        model.addAttribute("request", responseToARequest);
        return "whenCatchPersonInOneDay";
    }
}
