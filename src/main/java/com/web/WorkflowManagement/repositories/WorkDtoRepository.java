package com.web.WorkflowManagement.repositories;

import com.web.WorkflowManagement.dto.WorkDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WorkDtoRepository extends JpaRepository<WorkDto, Long> {

    @Query(value = "select e.id, e.id_task, t.title, e.description, t.time , e.status, e.iduser, e.datetime from employee_work e, Task t  where e.iduser = :id_user and e.datetime LIKE CONCAT('%',:datetime,'%') and e.id_task = t.id", nativeQuery = true)
    List<WorkDto> findAllByIduserDatetime(@Param("id_user") Integer id, @Param("datetime") String datetime);

    @Modifying
    @Transactional
    @Query(value = "update employee_work e set e.status = 1  where e.id = ?1", nativeQuery = true)
    void updateStatusById(Long id);

    @Modifying
    @Transactional
    @Query(value = "update employee_work e set e.status = 0  where e.id = ?1", nativeQuery = true)
    void updateStatusById0(Long id);

    @Modifying
    @Transactional
    @Query(value = "update employee_work e set e.description = :description  where e.id = :id", nativeQuery = true)
    void updateDescriptionById(@Param("description") String description, @Param("id")  Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employee_work e where e.id = :id and e.datetime LIKE CONCAT('%',:datetime,'%')", nativeQuery = true)
    void deleteById(@Param("id")  Long id, @Param("datetime") String datetime);

    @Query(value = "select e.id, e.id_task , t.title, e.description, e.datetime, t.time , e.status, e.iduser\n" +
            "from employee_work e, Task t\n" +
            " where e.iduser = ?1 and e.datetime like CONCAT('%',?2,'%') and e.status = ?3\n" +
            " and e.id_task = t.id;", nativeQuery = true)
    List<WorkDto> getListWDtoByStatusIdUserDate(Integer id, String month, Integer status);
}
