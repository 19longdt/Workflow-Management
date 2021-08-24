package com.web.WorkflowManagement.service;

import com.web.WorkflowManagement.dto.WorkDto;
import com.web.WorkflowManagement.model.Employee_Work;
import com.web.WorkflowManagement.model.Task;
import com.web.WorkflowManagement.model.Task_Type;
import com.web.WorkflowManagement.repositories.Employee_WorkRepository;
import com.web.WorkflowManagement.repositories.WorkDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Empoyee_WorkService {
    @Autowired
    private Employee_WorkRepository employee_workRepository;

    String[] today = Calendar.getInstance().getTime().toString().split(" ");
    String date = today[0] +" " + today[1]+" " + today[2];

    public void addEmployee_Work(List<Task> listTask, int userid){
//        if(listTask != null){
//            String[] today = Calendar.getInstance().getTime().toString().split(" ");
//            employee_workRepository.deleteByIdUser(userid, today[0]+" " + today[1]+" " + today[2]);
//        }

        int count = listTask.size();
        for (int i = 0; i < count; i++) {
            Employee_Work e = new Employee_Work(Integer.parseInt(listTask.get(i).getId().toString()),
                    date,
                    0, 1);
            System.out.println(e.getId_task());
            employee_workRepository.insertWork(e.getId_task(), e.getDatetime(), userid);
        }
    }

    public List<Employee_Work> findAll(int idUser) {

        // System.out.println(today[0] +" " + today[1]+" " + today[2] + " "+ idUser);
        return employee_workRepository.findAll(idUser, today[0] +" " + today[1]+" " + today[2]);
    }


}
