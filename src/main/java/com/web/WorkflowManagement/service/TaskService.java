package com.web.WorkflowManagement.service;

import com.web.WorkflowManagement.model.Employee_Work;
import com.web.WorkflowManagement.model.Task;
import com.web.WorkflowManagement.repositories.Employee_WorkRepository;
import com.web.WorkflowManagement.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private Employee_WorkRepository employee_workRepository;

//    public List<Task> findAllByTypeId(int id) {
//        return taskRepository.findAllByTypeId(id);
//    }


//    public List<Task> findAllByTitle(String title) {
//        return taskRepository.findAllByTitle(title);
//    }

    String[] today = Calendar.getInstance().getTime().toString().split(" ");
    String day = today[0] + " " + today[1] + " " + today[2];

    public List<Task> findAllBy(String title, int typeId, int iduser) {
        if (title.isEmpty()) {
            if (typeId != 3) {
                return taskRepository.findAllByTypeId1(typeId);
            } else {
                return taskRepository.findAllByTypeId(typeId, iduser);
            }

        } else if (!title.isEmpty() && !String.valueOf(typeId).isEmpty()) {

            return taskRepository.findAllByTitle(title, iduser);
        } else {
            return taskRepository.findAllByTitle(title, iduser);
        }
    }

    public String addNewTask(String title, int time, int iduser) {
        String a = ""; int timeAgo = 0;
        List<Task> list = findAllByIdUserAndToday(iduser);
        for (Task t : list) {
            timeAgo += t.getTime();
        }
        if(timeAgo + time <= 8){
            try {
                int c = taskRepository.getCountTask();
                taskRepository.addNewTask(title, time, iduser);
                int c1 = taskRepository.getCountTask();

                if(c == c1){
                    employee_workRepository.insertWork(taskRepository.getIdTaskbyTille(title), day , iduser);
                }else{
                    employee_workRepository.insertWork(taskRepository.getIdLast(), day , iduser);
                }

                return "ADD NEW TASK SUCCESSFUL!";
            } catch (Exception e) {
                return e.getMessage();
            }
        }else{
            return "Total time not greater than 8 hour";
        }
    }

    public List<Task> findByListId(List<String> listId, int iduser, List<Task> listTemporary) {
        int timeAgo = 0;
        for (Task t : listTemporary) {
            timeAgo += t.getTime();
        }

        if(timeAgo <= 8){
            int countTime = 0;
            for (String s : listId) {
                Task task = taskRepository.findTaskById(Long.valueOf(s));
                countTime += task.getTime();

                if (countTime + timeAgo > 8) {
                    return null;
                }
                listTemporary.add(task);
            }
            return listTemporary;
        }else{
            return null;
        }

    }

    public List<Task> findAllByIdUserAndToday(int idUser) {
        return taskRepository.findAllByIdUserAndToday(idUser, day);
    }
}
