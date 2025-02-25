package org.example.persist;

import java.time.LocalDate;
import java.util.List;

import org.example.constatnts.TaskStatus;
import org.example.persist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
	List<TaskEntity> findAllByDueDate(LocalDate dueDate);

	List<TaskEntity> findAllByStatus(TaskStatus taskStatus);
}
