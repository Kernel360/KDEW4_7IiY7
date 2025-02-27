package org.example.model.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.example.constatnts.TaskStatus;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskService taskService;

	@Test
	@DisplayName("할일 추가 테스트")
	void add() {
		when(taskRepository.save(any(TaskEntity.class)))
			.thenAnswer(invocationOnMock -> {
				var e = (TaskEntity) invocationOnMock.getArgument(0);
				e.setId(1L);
				e.setCreatedAt(LocalDateTime.now());
				e.setUpdatedAt(LocalDateTime.now());
				return e;
			});

		var title = "Test";
		var description = "Test description";
		var dueDate = LocalDate.now().toString();

		var actual = taskService.add(title, description, dueDate);

		verify(taskRepository).save(any(TaskEntity.class));

		assertEquals(actual.getId(), 1L);
		assertEquals(actual.getTitle(), title);
		assertEquals(actual.getDescription(), description);
		assertEquals(actual.getDueDate(), dueDate);
		assertEquals(actual.getStatus(), TaskStatus.TODO);
		assertNotNull(actual.getCreatedAt());
		assertNotNull(actual.getUpdatedAt());
	}
}