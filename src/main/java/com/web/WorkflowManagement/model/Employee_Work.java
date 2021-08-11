package com.web.WorkflowManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Employee_Work")
@AllArgsConstructor
@NoArgsConstructor
public class Employee_Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int id_task;
    private String description;
    private String datetime;
    private int status;
    private int iduser;

    public Employee_Work(int id_task, String datetime, int status, int iduser) {
        this.id_task = id_task;
        this.datetime = datetime;
        this.status = status;
        this.iduser = iduser;
    }
}
