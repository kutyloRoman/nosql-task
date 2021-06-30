package com.kutylo.nosqltask.task5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private String id;
  private String name;
  private String password;
  private int age;
  private String phone;

  List<Message> messages;
  List<Friendship> friendships;
  List<Movie> movies;

}
