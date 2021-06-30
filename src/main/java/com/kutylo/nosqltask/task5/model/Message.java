package com.kutylo.nosqltask.task5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
  private String id;
  private String text;
  private String date;
  private String userId;

}
