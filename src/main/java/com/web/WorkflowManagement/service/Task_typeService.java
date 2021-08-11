package com.web.WorkflowManagement.service;

import com.web.WorkflowManagement.model.Task_Type;
import com.web.WorkflowManagement.repositories.Task_TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Task_typeService{

    @Autowired
    private Task_TypeRepository task_typeRepository;

    public List<Task_Type> findAll() {
        return task_typeRepository.findAll();
    }
}
