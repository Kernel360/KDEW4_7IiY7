package org.example.persist.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.example.constatnts.TaskStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TASK")
@DynamicInsert
@DynamicUpdate
public class TaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private TaskStatus status;

	private String title;

	private String description;

	private LocalDate dueDate;

	@CreationTimestamp
	@Column(insertable = false, updatable = false)
	private LocalDateTime createdAt;

	@CreationTimestamp
	@Column(insertable = false, updatable = false)
	private LocalDateTime updatedAt;

	public void updateTask(String title, String description, LocalDate dueDate) {
		if (!title.isEmpty()) {
			this.title = title;
		}
		if (!description.isEmpty()) {
			this.description = description;
		}
		if (Objects.nonNull(dueDate)) {
			this.dueDate = dueDate;
		}
	}

	public void updateStatus(TaskStatus status) {
		this.status = status;
	}
}
