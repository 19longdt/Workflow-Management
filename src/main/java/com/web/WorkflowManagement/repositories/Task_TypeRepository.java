package com.web.WorkflowManagement.repositories;

import com.web.WorkflowManagement.model.Task_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Task_TypeRepository extends JpaRepository<Task_Type, Long> {
}
