package com.kutylo.nosqltask.task2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
  @Id
  private String id;
  private String name;
  @TextIndexed
  private String description;
  private LocalDateTime createdDate;
  private LocalDateTime deadlineDate;
  private TaskCategory category;
  @DBRef
  private List<SubTask> subTasks;
}
