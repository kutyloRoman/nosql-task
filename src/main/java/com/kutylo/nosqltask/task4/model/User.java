package com.kutylo.nosqltask.task4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  private String id;
  private String name;
  private String password;
  private int age;
  private String phone;

  List<Message> messages;
  List<Friendship> friendships;
  List<Movie> movies;

}
