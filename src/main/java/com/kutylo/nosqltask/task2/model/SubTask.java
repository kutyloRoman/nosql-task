package com.kutylo.nosqltask.task2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("subtask")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {
  @Id
  private String id;
  @TextIndexed
  private String name;
  private String description;
}
