package org.example.persist;

import java.time.LocalDate;
import java.util.List;
import java.util.zip.ZipFile;

import org.example.constatnts.TaskStatus;
import org.example.model.Task;
import org.example.persist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
	List<TaskEntity> findAllByDueDate(LocalDate dueDate);

	List<TaskEntity> findAllByStatus(TaskStatus taskStatus);
}
