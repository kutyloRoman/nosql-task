package com.kutylo.nosqltask.task2.repository.custom;

import com.kutylo.nosqltask.task2.model.SubTask;

import java.util.List;

public interface CustomSubtaskRepository {
  List<SubTask> getTasksByWorldInSubTaskName(String world);

  void insertSubtasks(List<SubTask> subTasks, String id);

  void updateSubtasks(List<SubTask> subTasks, String id);

  void deleteAllSubtasks(String id);
}
