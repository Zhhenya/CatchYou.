package com.example.demo.Controllers;

import com.example.demo.Constant;
import com.example.demo.request.GetRequest;
import com.example.demo.request.ParseFile;
import com.example.demo.request.ResponseToARequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CatchAllPersonsController {

    @RequestMapping(value = "/catchAllPersonsWithProbability", method = RequestMethod.POST)
    public String catchPersonGet(Model model){
        model.addAttribute("request", new GetRequest() );
        return "catchAllPersonsWithProbability";
    }

    @RequestMapping(value = "/afterCatchAllPersons", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String whenCatchPerson(Model model, GetRequest getRequest){
        ParseFile.clear();
        model.addAttribute("exception", "");
        if(getRequest.getProbability() >= 0 && getRequest.getProbability() <= 100){
            if (ParseFile.parseFileForAllPersons(getRequest.getProbability()) != false){
               model.addAttribute("request", getRequest.getProbability());
                model.addAttribute("allPersonRequest", ParseFile.getAllPersonRequest());
            }
            else
                model.addAttribute("exception", "Не найдено");
        }
        else {
            model.addAttribute("exception", "Неверный формат входных данных");
        }
        return "whenToCatchAllPersons";
    }
}
