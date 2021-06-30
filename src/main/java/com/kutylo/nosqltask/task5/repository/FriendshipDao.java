package com.kutylo.nosqltask.task5.repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.kutylo.nosqltask.task3.CassandraConnector;

public class FriendshipDao {

    private final Session session;

    public FriendshipDao(CassandraConnector cassandraConnector) {
        cassandraConnector.connect("127.0.0.2", 9042);
        this.session = cassandraConnector.getSession();
    }

    public long getMaxNumberOfNewFriendship(int userId, int month, int year) {
        String query = String.format("SELECT MAX(count) as count FROM db_network.user_friendship_count_by_month" +
                " WHERE month = %s AND year = %s;", month, year);

        ResultSet rs = session.execute(query);

        return rs.one().getLong("count");
    }

}