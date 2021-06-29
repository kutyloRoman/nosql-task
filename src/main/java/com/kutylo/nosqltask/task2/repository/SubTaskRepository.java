package com.kutylo.nosqltask.task2.repository;

import com.kutylo.nosqltask.task2.model.SubTask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubTaskRepository extends MongoRepository<SubTask, String> {

}
