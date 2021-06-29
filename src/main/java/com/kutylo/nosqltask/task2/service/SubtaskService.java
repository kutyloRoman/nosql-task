package com.kutylo.nosqltask.task2.service;

import com.kutylo.nosqltask.task2.model.SubTask;

import java.util.List;

public interface SubtaskService {
  List<SubTask> getAllSubtaskByTaskId(String id);

  void insertSubtasks(List<SubTask> subTasks, String id);

  void updateSubtasks(List<SubTask> subTasks, String id);
}
