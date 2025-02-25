package org.example.web;

import java.util.Optional;

import org.example.model.Task;
import org.example.model.service.TaskService;
import org.example.web.vo.TaskRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/tasks")
@Controller
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;

	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody TaskRequest request) {
		Task result = taskService.add(request.title(), request.description(), request.dueDate());
		return ResponseEntity.ok(result);
	}

	@GetMapping
	public ResponseEntity<?> getTasks(Optional<String> dueDate) {
		if (dueDate.isPresent()) {
			return ResponseEntity.ok(taskService.getByDueDate(dueDate.get()));
		}
		return ResponseEntity.ok(taskService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTask(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.getOne(id));
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<?> getTasksByStatus(@PathVariable String status) {
		return ResponseEntity.ok(taskService.getByTaskStatus(status));
	}


}

/*
POST 	/tasks 	{ "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01" } 	{"id": 1, "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01", "status": "TODO"} 	새로운 할 일 생성
GET 	/tasks 	dueDate (optional) 	[{"id": 1, "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01", "status": "TODO"}, {"id": 2, "title": "Task 2", "description": "Do something else", "dueDate": "2023-05-01", "status": "IN_PROGRESS"}] 	모든 할 일 조회(마감일이 있을 경우, 마감일에 해당하는 할 일 조회)
GET 	/tasks/{id} 	N/A 	{"id": 1, "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01", "status": "TODO"} 	특정 ID 에 해당하는 할 일 조회
GET 	/tasks/status/{status} 	N/A 	[{"id": 1, "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01", "status": "TODO"}, {"id": 3, "title": "Task 3", "description": "Do something else", "dueDate": "2023-05-02", "status": "TODO"}] 	특정 상태에 해당하는 할 일 모두 조회
PUT 	/tasks/{id} 	{ "title": "Updated Task 1", "description": "Do something else", "dueDate": "2023-05-02" } 	{"id": 1, "title": "Updated Task 1", "description": "Do something else", "dueDate": "2023-05-02", "status": "TODO"} 	특정 ID 에 해당하는 할 일 수정
PATCH 	/tasks/{id}/status 	{ "status": "IN_PROGRESS" } 	{"id": 1, "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01", "status": "IN_PROGRESS"} 	특정 ID 에 해당하는 할 일의 상태 변경
DELETE 	/tasks/{id} 	N/A 	{ "success": true }
 */