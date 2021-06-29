package com.kutylo.nosqltask.task2.service;

import com.kutylo.nosqltask.task2.model.Task;
import com.kutylo.nosqltask.task2.model.TaskCategory;
import com.kutylo.nosqltask.task2.repository.TaskRepository;
import com.kutylo.nosqltask.task2.repository.custom.CustomTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private TaskRepository taskRepository;
  private CustomTaskRepository customTaskRepository;

  @Override
  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  @Override
  public void insertTask(Task task) {
    taskRepository.save(task);
  }

  @Override
  public void updateTask(String id, Task task) {
    task.setId(id);
    taskRepository.save(task);
  }

  @Override
  public void delete(String id) {
    taskRepository.deleteById(id);
  }

  @Override
  public List<Task> getOverdueTasks(LocalDateTime nowDate) {
    return taskRepository.getOverdueTasks(LocalDateTime.now());
  }

  @Override
  public List<Task> findTasksByCategory(TaskCategory category) {
    return taskRepository.findTasksByCategory(category);
  }

  @Override
  public List<Task> getTasksByWorldInDescription(String world) {
    return customTaskRepository.getTasksByWorldInDescription(world);
  }


}
