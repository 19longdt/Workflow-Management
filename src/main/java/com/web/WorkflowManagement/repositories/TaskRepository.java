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
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.type_id = :type_id and t.id_user = :id_user" )
    List<Task> findAllByTypeId(@Param("type_id") Integer id, @Param("id_user") Integer id_user);

    @Query("SELECT t FROM Task t WHERE t.type_id = :type_id " )
    List<Task> findAllByTypeId1(@Param("type_id") Integer id);

    @Query("SELECT t FROM Task t WHERE t.id = :id")
    Task findTaskById(@Param("id") Long id);

    @Query("SELECT t FROM Task t WHERE t.title LIKE CONCAT('%',:title,'%') or t.title LIKE CONCAT('%',:title,'%') and t.id_user = :id_user")
    List<Task> findAllByTitle(@Param("title") String title, @Param("id_user") Integer id_user);

    @Query(value = "SELECT t.* FROM Task t JOIN Employee_Work e WHERE t.id = e.id_task  and e.iduser = :iduser and e.datetime LIKE CONCAT('%',:datetime,'%') ",nativeQuery = true)
    List<Task> findAllByIdUserAndToday (@Param("iduser") Integer id, @Param("datetime") String datetime);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Task (title, time, type_id, id_user)\n" +
            "SELECT * FROM (SELECT :title as title, :time as time, 3 as type_id, :id as id_user) AS tmp\n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT * FROM Task where title = :title and type_id = 3 and time = :time and id_user = :id\n" +
            ") LIMIT 1;", nativeQuery = true)
    void addNewTask(@Param("title") String title, @Param("time") Integer time, @Param("id") Integer id);

    @Query(value = "select id from Task order by id desc LIMIT 1;", nativeQuery = true)
    int getIdLast();

    @Query(value = "SELECT COUNT(*) from task_management.task;", nativeQuery = true)
    int getCountTask();
    
    @Query(value = "SELECT id FROM task_management.task where title = ?1" , nativeQuery = true)
    int getIdTaskbyTille(String title);


}
