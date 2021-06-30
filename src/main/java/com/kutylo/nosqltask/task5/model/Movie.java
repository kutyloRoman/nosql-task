package com.kutylo.nosqltask.task5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
  private String id;
  private String name;
  private String description;
  private String userId;
}
