package com.web.WorkflowManagement.service;

import com.web.WorkflowManagement.dto.WorkDto;
import com.web.WorkflowManagement.repositories.Employee_WorkRepository;
import com.web.WorkflowManagement.repositories.WorkDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class WorkDtoService {
    @Autowired
    private WorkDtoRepository workDtoRepository;

    @Autowired
    private Employee_WorkRepository employee_workRepository;

    String[] today = Calendar.getInstance().getTime().toString().split(" ");
    String date = today[0] +" " + today[1]+" " + today[2];
//
//    public WorkDto WorkDtogetWorkDto(int id, int iduser){
//
//        // System.out.println(workDtoRepository.findAllByIduserDatetime(iduser, today[0] +" " + today[1]+" " + today[2]).get(0));
//        return workDtoRepository.findAllByIduserDatetime(iduser, date);
//    }

    public List<WorkDto> getAllWorkDto(int iduser){

       // System.out.println(workDtoRepository.findAllByIduserDatetime(iduser, today[0] +" " + today[1]+" " + today[2]).get(0));
        return workDtoRepository.findAllByIduserDatetime(iduser, date);
    }

    // hơi ngu
    public void updateWork(int user, List<String> des, List<Integer> idcheck){
        List<WorkDto> workDtoList = getAllWorkDto(user);
        List<Integer> listIdNotCheck = new ArrayList<>();

        for (WorkDto w : workDtoList) {
            int id = Integer.parseInt(w.getId().toString());
            for (int i = 0; i < idcheck.size(); i++){
                if(idcheck.get(i) == id){
                    workDtoRepository.updateStatusById(Long.valueOf(String.valueOf(idcheck.get(i))));
                    break;
                }if(i == idcheck.size() - 1){
                    listIdNotCheck.add(id);
                }
            }
        }
        for (Integer k : listIdNotCheck){
            workDtoRepository.updateStatusById0(Long.valueOf(String.valueOf(k)));
        }

        for (int i = 0; i < workDtoList.size(); i++) {
            int id = Integer.parseInt(workDtoList.get(i).getId().toString());
            String d = des.get(i);
            workDtoRepository.updateDescriptionById(d, Long.valueOf(String.valueOf(id)));
        }
    }

    public void deleteWork(List<Integer> id){
        for (Integer i : id){
            workDtoRepository.deleteById(Long.valueOf(String.valueOf(i)), date);
        }
    }

    public int getPerfomance(int iduser){
        return employee_workRepository.getworkperformance(iduser, date);
    }


}
