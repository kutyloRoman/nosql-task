package com.kutylo.nosqltask.task4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
  @Id
  private String id;
  private String text;
  private String date;
  private String userId;

}
