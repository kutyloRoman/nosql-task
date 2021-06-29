package com.kutylo.nosqltask.task2.repository;

import com.kutylo.nosqltask.task2.model.Task;
import com.kutylo.nosqltask.task2.model.TaskCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
  List<Task> findTasksByCategory(TaskCategory category);

  @Query("{'deadlineDate' : {$lt: ?0}}")
  List<Task> getOverdueTasks(LocalDateTime nowDate);

}
