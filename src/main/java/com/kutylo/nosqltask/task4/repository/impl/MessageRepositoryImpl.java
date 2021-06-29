package com.kutylo.nosqltask.task4.repository.impl;

import com.kutylo.nosqltask.task4.model.aggregationResults.MessageCount;
import com.kutylo.nosqltask.task4.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;


@Repository
@AllArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

  private final MongoTemplate mongoTemplate;
  private final String COLLECTION_NAME = "messages";
  private final String DAY_OF_WEEK_REGEX = "^%s-%s-\\d{4}\\s\\d{2}:\\d{2}:\\d{2}$";


  @Override
  public double getAverageMessageNumberByDay(int dayOfWeek) {
    int month = 0;
    List<MessageCount> messageCounts = new ArrayList<>();
    while (month <= 12) {
      MatchOperation filterMessages = match(Criteria.where("date").regex(getDatePattern(dayOfWeek, month)));
      GroupOperation groupOperation = group("date").count().as("messageCount");
      ProjectionOperation projectToMatchModel = project()
          .andExpression("date").as("date")
          .andExpression("messageCount").as("messageCount");

      Aggregation aggregation = newAggregation(filterMessages, groupOperation, projectToMatchModel);
      AggregationResults<MessageCount> results = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, MessageCount.class);
      messageCounts.addAll(results.getMappedResults());
      month++;
    }

    return getAverageMessageNumber(messageCounts);
  }

  private double getAverageMessageNumber(List<MessageCount> messageCounts) {
    return messageCounts.stream()
        .mapToInt(MessageCount::getMessageCount)
        .average()
        .orElse(0.0);

  }

  private String getDatePattern(int dayOfWeek, int months) {

    if (dayOfWeek < 10 && months < 10) {
      return String.format(DAY_OF_WEEK_REGEX, "0" + dayOfWeek, "0" + months);
    } else if (dayOfWeek < 10) {
      return String.format(DAY_OF_WEEK_REGEX, "0" + dayOfWeek, months);
    } else {
      return String.format(DAY_OF_WEEK_REGEX, dayOfWeek, "0" + months);
    }
  }

}
