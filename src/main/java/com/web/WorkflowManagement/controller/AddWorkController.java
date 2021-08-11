package com.web.WorkflowManagement.controller;

import com.web.WorkflowManagement.model.Task;
import com.web.WorkflowManagement.service.Empoyee_WorkService;
import com.web.WorkflowManagement.service.TaskService;
import com.web.WorkflowManagement.service.Task_typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class AddWorkController {

    int iduser = 1;
    boolean check = true;
    String addnewResult ="";
    int type = 0; String txtS= "";

    @Autowired
    private TaskService taskService;

    @Autowired
    private Task_typeService task_typeService;

    @Autowired
    private Empoyee_WorkService empoyee_workService;


    List<Task> listTemporary = new ArrayList<>();

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("type", task_typeService.findAll());
        model.addAttribute("task", new Task());
        //getTaskToday(model);
        if(check == true){
            listTemporary = taskService.findAllByIdUserAndToday(iduser);
            check = false;
        }
        model.addAttribute("addList",listTemporary);

//        listTemporary = new ArrayList<>();
        model.addAttribute("result", addnewResult);
        return "home";
    }

    @PostMapping(value = "/home", params = "getTask")
    public String getTask(Model model, @RequestParam(value = "txtsearch") String txtSearch, @RequestParam(value = "option") int option) {
        type = option;
        txtS= txtSearch;

        model.addAttribute("type", task_typeService.findAll());
        model.addAttribute("task", new Task());
        model.addAttribute("type_id", type);
        List<Task> list = taskService.findAllBy(txtSearch, option, iduser);
        model.addAttribute("listTask", list);

            model.addAttribute("addList", listTemporary);

        return "home";
    }

    @PostMapping(value = "/home", params = "addNewTask")
    public String addNewTask(Model model, @RequestParam(value = "titlenew") String title, @RequestParam(value = "timenew") int time) {
        //System.out.println(taskService.addNewTask(title, time, iduser));
        addnewResult = taskService.addNewTask(title, time, iduser);
        model.addAttribute("addList", listTemporary);
        check = true;
//        getTaskToday(model);
        return "redirect:/home";
    }

    @PostMapping(value = "/home", params = "addTask")
    public String addTask(Model model, @RequestParam(value = "cb") List<String> listID) {
        model.addAttribute("type", task_typeService.findAll());
        model.addAttribute("task", new Task());

        List<Task> listTask = taskService.findByListId(listID, iduser, listTemporary);
        if (listTask != null) {
            model.addAttribute("addList", listTask);
            empoyee_workService.addEmployee_Work(listTask, iduser);
            listTemporary  = listTask;
        } else {
            model.addAttribute("error", "Total time not greater than 8 hour");
            model.addAttribute("addList", listTemporary);
        }

        if(!txtS.equals("") || type!=0){
            model.addAttribute("type_id", type);
            List<Task> list = taskService.findAllBy(txtS, type, iduser);
            model.addAttribute("listTask", list);
        }else{
            model.addAttribute("listTask", new ArrayList<>());
        }

        return "home";
    }

    @PostMapping(value = "/home", params = "addWork")
    public String addWork(Model model) {
        model.addAttribute("type", task_typeService.findAll());
        model.addAttribute("task", new Task());

        empoyee_workService.addEmployee_Work(listTemporary, iduser);
        listTemporary = taskService.findAllByIdUserAndToday(iduser);
        model.addAttribute("addList", listTemporary);
        return "home";
    }

    @PostMapping(value = "/home", params = "refresh")
    public String refresh(Model model) {
        model.addAttribute("type", task_typeService.findAll());
        listTemporary = taskService.findAllByIdUserAndToday(iduser);
        model.addAttribute("addList", listTemporary);
        return "home";
    }
}
