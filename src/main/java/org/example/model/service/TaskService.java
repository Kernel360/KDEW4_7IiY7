package org.example.model.service;

import java.time.LocalDate;
import java.util.List;

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

	public List<Task> getAll() {
		return taskRepository.findAll()
			.stream()
			.map(this::entityToModel)
			.toList();
	}

	public List<Task> getByDueDate(String dueDate) {
		return taskRepository.findAllByDueDate(LocalDate.parse(dueDate))
			.stream()
			.map(this::entityToModel)
			.toList();
	}

	public List<Task> getByTaskStatus(String status) {
		return taskRepository.findAllByStatus(TaskStatus.valueOf(status))
			.stream()
			.map(this::entityToModel)
			.toList();
	}

	public Task getOne(Long id) {
		return entityToModel(getById(id));
	}

	private TaskEntity getById(Long id) {
		return taskRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Task not found"));
	}

	public Task update(Long id, String title, String description, String dueDate) {
		var taskEntity = getById(id);

		taskEntity.updateTask(title, description, LocalDate.parse(dueDate));
		var savedTaskEntity = taskRepository.save(taskEntity);

		return entityToModel(savedTaskEntity);
	}

	public Task updateStatus(Long id, String status) {
		var taskEntity = getById(id);

		taskEntity.updateStatus(TaskStatus.valueOf(status));
		var savedTaskEntity = taskRepository.save(taskEntity);

		return entityToModel(savedTaskEntity);
	}

	public boolean delete(Long id) {
		try {
			taskRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			log.error("delete failed id: {}", id);
			return false;
		}
	}
}
