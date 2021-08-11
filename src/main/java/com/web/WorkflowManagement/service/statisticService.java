package com.web.WorkflowManagement.service;

import com.web.WorkflowManagement.dto.WorkDto;
import com.web.WorkflowManagement.repositories.WorkDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class statisticService {
    @Autowired
    private WorkDtoRepository workDtoRepository;

    public List<String> getMonth(){
        String[] months = new DateFormatSymbols().getShortMonths();
        List<String> list = Stream.of(months).collect(Collectors.toList());
        return list;
    }

    public List<WorkDto> getListWDtoByStatusIdUserDate(int id, String date, int status){
        return workDtoRepository.getListWDtoByStatusIdUserDate(id, date, status);
    }
}
