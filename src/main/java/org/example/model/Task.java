package org.example.model;

import java.time.LocalDateTime;

import org.example.constatnts.TaskStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
	private Long id;
	private TaskStatus status;
	private String title;
	private String description;
	private String dueDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
