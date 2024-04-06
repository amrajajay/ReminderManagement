package com.se.ReminderManagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.se.ReminderManagement.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

}
