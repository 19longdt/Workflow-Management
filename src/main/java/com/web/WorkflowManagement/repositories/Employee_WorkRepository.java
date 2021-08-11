package com.web.WorkflowManagement.repositories;

import com.web.WorkflowManagement.model.Employee_Work;
import com.web.WorkflowManagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface Employee_WorkRepository extends JpaRepository<Employee_Work, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employee_work e where e.iduser = :id_user and e.datetime LIKE CONCAT('%',:datetime,'%')", nativeQuery = true)
     void deleteByIdUser(@Param("id_user") Integer id, @Param("datetime") String datetime);

    @Query(value ="select e from employee_work e where e.iduser = :id_user and e.datetime LIKE CONCAT('%',:datetime,'%')", nativeQuery = true)
    List<Employee_Work> findAll(@Param("id_user") Integer id, @Param("datetime") String datetime);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO employee_work (id_task, datetime, status, iduser)\n" +
            "SELECT * FROM (SELECT :id_task, :datetime, 0, :id_user) AS tmp\n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT * FROM employee_work where id_task = :id_task  and datetime = :datetime and iduser = :id_user\n" +
            ") LIMIT 1;", nativeQuery = true)
    void insertWork(@Param("id_task") Integer id,  @Param("datetime") String date, @Param("id_user") Integer id_user);

    @Query(value = "select round((\n" +
            "((SELECT count(*) FROM task_management.employee_work \n" +
            "where iduser = :id_user and datetime LIKE CONCAT('%',:datetime,'%') and status = 1) / (SELECT count(*) FROM task_management.employee_work \n" +
            "where iduser = :id_user and datetime LIKE CONCAT('%',:datetime,'%'))) * 100), 0);", nativeQuery = true)
    int getworkperformance (@Param("id_user") Integer iduser, @Param("datetime") String datetime);
}
