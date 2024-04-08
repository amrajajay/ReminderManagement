package com.se.ReminderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.se.ReminderManagement.entity.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, String> {

}
