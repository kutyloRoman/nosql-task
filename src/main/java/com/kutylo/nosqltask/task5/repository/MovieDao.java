package com.kutylo.nosqltask.task5.repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.kutylo.nosqltask.task3.CassandraConnector;

public class MovieDao {

    private final Session session;

    public MovieDao(CassandraConnector cassandraConnector) {
        cassandraConnector.connect("127.0.0.2", 9042);
        this.session = cassandraConnector.getSession();
    }

    public long getMinNumberOfWatchedMovies(int friendshipCount) {
        String query = String.format("SELECT MIN(movie_count) AS count FROM db_network.user_movie_count" +
                " WHERE friendship_count > %s ALLOW FILTERING;", friendshipCount);

        ResultSet rs = session.execute(query);

        return rs.one().getLong("count");
    }

}
