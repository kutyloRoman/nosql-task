package com.kutylo.nosqltask.task2.service;

import com.kutylo.nosqltask.task2.model.SubTask;
import com.kutylo.nosqltask.task2.repository.custom.CustomSubtaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubtaskServiceImpl implements SubtaskService {
  private CustomSubtaskRepository subtaskRepository;

  @Override
  public List<SubTask> getAllSubtaskByTaskId(String id) {
    return null;
  }

  @Override
  public void insertSubtasks(List<SubTask> subTasks, String id) {

  }

  @Override
  public void updateSubtasks(List<SubTask> subTasks, String id) {

  }
}
