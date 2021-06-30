package com.kutylo.nosqltask.task5.repository;

import com.datastax.driver.core.Session;
import com.kutylo.nosqltask.task3.CassandraConnector;

public class DDLDao {

    private final Session session;

    public DDLDao(CassandraConnector cassandraConnector) {
        cassandraConnector.connect("127.0.0.2", 9042);
        this.session = cassandraConnector.getSession();
    }

    public void createKeySpace() {
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append("db_network").append(" WITH replication = {")
                        .append("'class':'").append("SimpleStrategy")
                        .append("','replication_factor':").append(1)
                        .append("};");

        String query = sb.toString();
        session.execute(query);
    }

    public void createUserMessageByDate() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append("db_network.user_message_count_by_date(")
                .append("user_id int, ")
                .append("date date, ")
                .append("count counter, ")
                .append("primary key (user_id, date))");

        String query = sb.toString();
        session.execute(query);
    }

    public void createUserFriendshipCountByMonth() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append("db_network.user_friendship_count_by_month(")
                .append("user_id int, ")
                .append("months int, ")
                .append("year int, ")
                .append("count counter, ")
                .append("primary key (user_id, months, year))");

        String query = sb.toString();
        session.execute(query);
    }

    public void createUserMovieCount() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append("db_network.user_movie_count(")
                .append("user_id int, ")
                .append("friendship_count counter, ")
                .append("movie_count counter, ")
                .append("primary key (user_id))");
        String query = sb.toString();
        session.execute(query);
    }

}
