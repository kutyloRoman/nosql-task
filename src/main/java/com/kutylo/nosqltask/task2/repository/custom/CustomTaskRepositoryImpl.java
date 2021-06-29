package com.kutylo.nosqltask.task2.repository.custom;

import com.kutylo.nosqltask.task2.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CustomTaskRepositoryImpl implements CustomTaskRepository {

  private final MongoTemplate mongoTemplate;
  private final String COLLECTION_NAME = "tasks";


  @Override
  public List<Task> getTasksByWorldInDescription(String world) {
    TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
        .onField("description")
        .build();
    mongoTemplate.indexOps(Task.class).ensureIndex(textIndex);

    TextCriteria criteria = new TextCriteria().matchingAny(world);
    Query query = TextQuery.queryText(criteria).sortByScore();
    return mongoTemplate.find(query, Task.class, COLLECTION_NAME);
  }


}
