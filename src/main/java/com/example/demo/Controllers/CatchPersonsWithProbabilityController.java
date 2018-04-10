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

import java.util.Vector;

@Controller
public class CatchPersonsWithProbabilityController {
    @RequestMapping(value = "/catchPersonWithProbability", method = RequestMethod.POST)
    public String catchDay(Model model){
        model.addAttribute("request", new GetRequest() );
        return "catchPersonWithProbability";
    }

    @RequestMapping(value = "/afterCatchProbability", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String whenCatchDay(Model model, GetRequest getRequest){
        ResponseToARequest responseToARequest = new ResponseToARequest();
        Vector<String> nameOfTheDays = new Vector<>();
        ParseFile.clear();
        model.addAttribute("exception", "");
        if(getRequest.getName() != "" && getRequest.getProbability() >= 0 && getRequest.getProbability() <= 100) {
            if(ParseFile.parseFileForOnePersone(getRequest.getName())) {
                responseToARequest.setProbabilityOfArrivalOfAGivenDayOfTheWeek(ExecuteRequest.probabilityOfArrivalOfAnEmployeeForEachDayOfTheWeek(ParseFile.getPerson()));
                responseToARequest.setName(getRequest.getName());
                Vector<Double> prob = ExecuteRequest.getDayForProbability(responseToARequest.getProbabilityOfArrivalOfAGivenDayOfTheWeek(), nameOfTheDays, getRequest.getProbability());
                responseToARequest.setProbabilityOfArrivalOfAGivenDayOfTheWeek(prob);
                responseToARequest.setPersonneNumber(ParseFile.getPerson().getPersonneNumber());

            }else
                model.addAttribute("exception", "Файл пуст, либо не существует");
        } else
            model.addAttribute("exception", "Неверный формат входных данных");
        model.addAttribute("nameOfTheDays", nameOfTheDays);
        model.addAttribute("request", responseToARequest);
        return "whenCatchWithProbability";
    }
}
