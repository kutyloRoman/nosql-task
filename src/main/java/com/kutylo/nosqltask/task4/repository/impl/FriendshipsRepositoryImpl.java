package com.kutylo.nosqltask.task4.repository.impl;

import com.kutylo.nosqltask.task4.model.aggregationResults.FriendshipMax;
import com.kutylo.nosqltask.task4.repository.FriendshipsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

@AllArgsConstructor
@Repository
public class FriendshipsRepositoryImpl implements FriendshipsRepository {
  private final MongoTemplate mongoTemplate;
  private final String COLLECTION_NAME = "friendships";
  private final String MONTH_REGEX = "^\\d{2}-%s-\\d{4}\\s\\d{2}:\\d{2}:\\d{2}$";

  @Override
  public int getMaxNumberOfNewFriendship(int month) {
    GroupOperation usersWithFriendships = group("userId").count().as("friendshipCount");
    MatchOperation filteredUsersByDate = match(new Criteria("connectionDate").regex(getMonthPattern(month)));
    SortOperation sortByCount = sort(Sort.by(Sort.Direction.DESC, "friendshipCount"));
    LimitOperation limitToOnlyFirstDoc = limit(1);
    ProjectionOperation projectToMatchModel = project()
        .andExpression("_id").as("userId")
        .andExpression("friendshipCount").as("friendshipCount");

    Aggregation aggregation = newAggregation(
        filteredUsersByDate, usersWithFriendships, sortByCount, limitToOnlyFirstDoc, projectToMatchModel);

    AggregationResults<FriendshipMax> result = mongoTemplate
        .aggregate(aggregation, COLLECTION_NAME, FriendshipMax.class);
    return Optional.ofNullable(result.getMappedResults().get(0).getFriendshipCount()).orElse(0);
  }


  private String getMonthPattern(int month) {
    return month < 10 ? String.format(MONTH_REGEX, "0" + month)
        : String.format(MONTH_REGEX, month);
  }
}
