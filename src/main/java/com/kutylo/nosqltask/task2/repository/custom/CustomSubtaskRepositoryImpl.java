package com.kutylo.nosqltask.task2.repository.custom;

import com.kutylo.nosqltask.task2.model.SubTask;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class CustomSubtaskRepositoryImpl implements CustomSubtaskRepository {

  private final MongoTemplate mongoTemplate;
  private final String COLLECTION_NAME = "subtask";

  @Override
  public List<SubTask> getTasksByWorldInSubTaskName(String world) {
    TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
        .onField("name")
        .build();
    mongoTemplate.indexOps(SubTask.class).ensureIndex(textIndex);

    TextCriteria criteria = new TextCriteria().matchingAny(world);
    Query query = TextQuery.queryText(criteria).sortByScore();
    return mongoTemplate.find(query, SubTask.class, COLLECTION_NAME);
  }

  @Override
  public void insertSubtasks(List<SubTask> subTasks, String id) {
    Query query = new Query(Criteria.where("_id").is(id));
    List<SubTask> existSubtasks = mongoTemplate.find(query, SubTask.class);
    existSubtasks.addAll(subTasks);

    Update update = new Update();
    update.set("subtask", subTasks);

    mongoTemplate.updateFirst(query, update, SubTask.class);
  }

  @Override
  public void updateSubtasks(List<SubTask> subTasks, String id) {
    Query query = new Query(Criteria.where("_id").is(id));
    Update update = new Update();
    update.set("subtask", subTasks);

    mongoTemplate.updateFirst(query, update, SubTask.class);
  }

  @Override
  public void deleteAllSubtasks(String id) {
    Query query = new Query(Criteria.where("_id").is(id));
    mongoTemplate.remove(query, SubTask.class);

  }
}
