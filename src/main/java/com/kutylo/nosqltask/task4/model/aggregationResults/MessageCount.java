package com.kutylo.nosqltask.task4.model.aggregationResults;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageCount {
  private String date;
  private int messageCount;
}
