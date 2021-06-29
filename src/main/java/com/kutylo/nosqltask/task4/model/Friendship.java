package com.kutylo.nosqltask.task4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("friendships")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friendship {
  @Id
  private String id;
  private String userId;
  private String connectUserId;
  private String connectionDate;
}
