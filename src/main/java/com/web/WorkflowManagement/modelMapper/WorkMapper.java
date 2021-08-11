package com.web.WorkflowManagement.modelMapper;

import com.web.WorkflowManagement.dto.WorkDto;
import com.web.WorkflowManagement.model.Employee_Work;
import com.web.WorkflowManagement.service.Empoyee_WorkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class WorkMapper {
//    @Autowired
//    public ModelMapper modelMapper;
//
//
//    @Autowired
//    private Empoyee_WorkService empoyee_workService;
//
//    public WorkDto convertToDto(Employee_Work ew, String title) {
//        WorkDto workDto = modelMapper.map(ew, WorkDto.class);
//        workDto.setTitle(title);
//        return workDto;
//    }
//
//    public List<WorkDto> convertToListWorkDto(int Iduser, List<String> title) {
//        List<Employee_Work> listEmployee_Work = empoyee_workService.findAll(Iduser);
////        List<WorkDto> listWorkDTO =  listEmployee_Work.stream().map(this::convertToDto()).collect(Collectors.toList());
//        //        TodoDTO todoDto = modelMapper.map(todo, TodoDTO.class);
//        //        todoDto.setDatetime(Calendar.getInstance().getTime());
//
//        List<WorkDto> listWorkDTO = new ArrayList<>();
//
//        for (Employee_Work ew : listEmployee_Work){
//            listWorkDTO.add(convertToDto(ew, ew.get))
//        }
//        return listTodoDTO;
//    }
//
//    private Todo convertToEntity(TodoDTO todoDto) throws ParseException {
//        Todo todo = modelMapper.map(todoDto, Todo.class);
////        todo.setSubmissionDate(postDto.getSubmissionDateConverted(
////                userService.getCurrentUser().getPreference().getTimezone()));
//
//        if (todoDto.getId() != null) {
//            Todo oldTodo = todoService.getTodobyId(todoDto.getId());
//            todo.setNote("New note");
//        }
//        return todo;
//    }
}
