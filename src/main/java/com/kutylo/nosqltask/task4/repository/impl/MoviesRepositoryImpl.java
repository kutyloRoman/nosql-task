package com.kutylo.nosqltask.task4.repository.impl;

import com.kutylo.nosqltask.task4.model.aggregationResults.MinWatchedMovies;
import com.kutylo.nosqltask.task4.repository.MoviesRepository;
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

@Repository
@AllArgsConstructor
public class MoviesRepositoryImpl implements MoviesRepository {

  private final MongoTemplate mongoTemplate;
  private final String COLLECTION_NAME = "movies";

  @Override
  public int getMinNumberOfWatchedMovies(int friendshipAmount) {
    GroupOperation usersWithFriendships = group("friendships").count().as("friendshipCount");
    MatchOperation filterUsers = match(new Criteria("friendshipCount").gt(friendshipAmount));
    GroupOperation watchedMovies = group("movies").count().as("watchedMovies");
    SortOperation sortByCount = sort(Sort.by(Sort.Direction.DESC, "watchedMovies"));
    LimitOperation limitToOnlyFirstDoc = limit(1);
    ProjectionOperation projectToMatchModel = project()
        .andExpression("watchedMovies").as("moviesCount");

    Aggregation aggregation = newAggregation(
        usersWithFriendships, filterUsers, watchedMovies, sortByCount, limitToOnlyFirstDoc, projectToMatchModel);

    AggregationResults<MinWatchedMovies> result = mongoTemplate
        .aggregate(aggregation, COLLECTION_NAME, MinWatchedMovies.class);
    return Optional.of(result.getMappedResults().get(0).getMoviesCount()).orElse(0);
  }
}
