package com.kutylo.nosqltask.task5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friendship {
  private String id;
  private String userId;
  private String connectUserId;
  private String connectionDate;
}
