package com.kutylo.nosqltask.task2.repository.custom;

import com.kutylo.nosqltask.task2.model.Task;

import java.util.List;

public interface CustomTaskRepository {
  List<Task> getTasksByWorldInDescription(String world);
}
