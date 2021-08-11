package com.web.WorkflowManagement.controller;

import com.web.WorkflowManagement.repositories.Employee_WorkRepository;
import com.web.WorkflowManagement.service.Empoyee_WorkService;
import com.web.WorkflowManagement.service.TaskService;
import com.web.WorkflowManagement.service.WorkDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;

@Controller
public class ManageController {

    int iduser = 1;
    @Autowired
    private TaskService taskService;

    @Autowired
    private WorkDtoService workDtoService;



    String[] datetime = Calendar.getInstance().getTime().toString().split(" ");
    String today = datetime[0] +" " + datetime[1]+" " + datetime[2];
    @GetMapping(value = "manager")
    private String showWork(Model model){
        int per = workDtoService.getPerfomance(iduser);
        model.addAttribute("per", per);
        model.addAttribute("listWork", workDtoService.getAllWorkDto(iduser));
        model.addAttribute("today", today);
        return "manager";
    }

    @PostMapping(value = "manager", params = "updateWork")
    private String updateWork(Model model, @RequestParam (value = "description")List<String> description, @RequestParam (value = "status")List<Integer> status){
        workDtoService.updateWork(iduser, description, status);
        return "redirect:/manager";
    }

    @PostMapping(value = "manager", params = "deleteWork")
    private String deleteWork(Model model, @RequestParam (value = "action")List<Integer> id){
        workDtoService.deleteWork(id);
        return "redirect:/manager";
    }
}
