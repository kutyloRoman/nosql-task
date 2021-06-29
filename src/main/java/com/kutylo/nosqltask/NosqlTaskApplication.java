package com.kutylo.nosqltask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableMongoRepositories(basePackageClasses = TaskRepository.class)
public class NosqlTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(NosqlTaskApplication.class, args);
  }

}
