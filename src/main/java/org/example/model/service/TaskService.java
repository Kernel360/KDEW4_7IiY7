package org.example.model.service;

import java.time.LocalDate;

import org.example.constatnts.TaskStatus;
import org.example.model.Task;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

	private final TaskRepository taskRepository;

	public Task add(String title, String description, String dueDate) {
		var taskEntity = TaskEntity.builder()
			.title(title)
			.description(description)
			.dueDate(LocalDate.parse(dueDate))
			.status(TaskStatus.TODO)
			.build();

		var savedTaskEntity = taskRepository.save(taskEntity);

		return entityToModel(savedTaskEntity);
	}

	private Task entityToModel(TaskEntity entity) {
		return Task.builder()
			.id(entity.getId())
			.title(entity.getTitle())
			.description(entity.getDescription())
			.dueDate(entity.getDueDate().toString())
			.status(entity.getStatus())
			.createdAt(entity.getCreatedAt())
			.updatedAt(entity.getUpdatedAt())
			.build();
	}
}
