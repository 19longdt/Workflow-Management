package com.web.WorkflowManagement.controller;

import com.web.WorkflowManagement.service.statisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class StatisticController {
    @Autowired
    private statisticService statisticService;

    int iduser = 1;
    String monthSelected = "";
    @GetMapping(value = "/statistic")
    public String getstatistic(Model model){
        model.addAttribute("month", statisticService.getMonth());

        String month = LocalDate.now().getMonth().toString().substring(0,3);
        String firstLetStr = month.substring(0, 1);
        String remLetStr = month.substring(1);
        firstLetStr = firstLetStr.toUpperCase();

        model.addAttribute("getMonth", firstLetStr + remLetStr);
        model.addAttribute("listWork", null);
        show(model, "Aug");
        return "statistic";
    }

    @PostMapping(value = "/statistic", params = "select")
    public String selectMonth(Model model, @RequestParam(value = "month") String month){
        monthSelected = month;
        show(model, month);
        return "statistic";
    }

    @PostMapping(value = "/statistic", params = "completed")
    public String showCompleted(Model model){
        show(model, monthSelected);
        model.addAttribute("listWork", statisticService.getListWDtoByStatusIdUserDate(iduser,  monthSelected, 1));
        return "statistic";
    }

    @PostMapping(value = "/statistic", params = "unfinished")
    public String showUnfinished(Model model){
        show(model, monthSelected);
        model.addAttribute("listWork", statisticService.getListWDtoByStatusIdUserDate(iduser,  monthSelected, 0));
        return "statistic";
    }

    public void show(Model model, String month){


        model.addAttribute("getMonth", month);
        model.addAttribute("month", statisticService.getMonth());
        model.addAttribute("completed", statisticService.getListWDtoByStatusIdUserDate(iduser, month , 1).size());
        model.addAttribute("unfinished", statisticService.getListWDtoByStatusIdUserDate(iduser,  month, 0).size());
    }
}
