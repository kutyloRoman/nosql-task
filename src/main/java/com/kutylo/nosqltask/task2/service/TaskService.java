package com.kutylo.nosqltask.task2.service;

import com.kutylo.nosqltask.task2.model.Task;
import com.kutylo.nosqltask.task2.model.TaskCategory;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
  List<Task> getAllTasks();

  void insertTask(Task task);

  void updateTask(String id, Task task);

  void delete(String id);

  List<Task> getOverdueTasks(LocalDateTime nowDate);

  List<Task> findTasksByCategory(TaskCategory category);

  List<Task> getTasksByWorldInDescription(String world);
}
