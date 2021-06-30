package com.kutylo.nosqltask.task5.repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.kutylo.nosqltask.task3.CassandraConnector;
import com.kutylo.nosqltask.task5.model.aggregationResults.MessageCount;


import java.util.ArrayList;
import java.util.List;

public class MessageDao {

    private final Session session;

    public MessageDao(CassandraConnector cassandraConnector) {
        cassandraConnector.connect("127.0.0.2", 9042);
        this.session = cassandraConnector.getSession();
    }

    public double getAverageMessageNumberByDay(int userId, int day) {
        List<String> queries = new ArrayList<>();
        int month = 1;
        while (month <= 12) {
            queries.add(String.format("SELECT count FROM db_network.user_message_count_by_date" +
                            " WHERE user_id = %s AND date = '%s';", userId, "2021-10-10"));
            month++;
        }

        List<Long> messageCountByDay = new ArrayList<>();

        for (String query: queries) {
            System.out.println(query);
            ResultSet rs = session.execute(query);
            messageCountByDay.add(rs.one().getLong("count"));
        }

        return messageCountByDay.stream()
                .mapToLong(Long::longValue)
                .average().getAsDouble();
    }

    private double getAverageMessageNumber(List<MessageCount> messageCounts) {
        return messageCounts.stream()
                .mapToInt(MessageCount::getMessageCount)
                .average()
                .orElse(0.0);

    }

    private String getDate(int dayOfWeek, int months) {

        String DAY_OF_WEEK_REGEX = "%s-%s-%s";
        if (dayOfWeek < 10 && months < 10) {
            return String.format(DAY_OF_WEEK_REGEX, "2021", "0" + dayOfWeek, "0" + months);
        } else if (dayOfWeek < 10) {
            return String.format(DAY_OF_WEEK_REGEX, "2021", "0" + dayOfWeek, months);
        } else {
            return String.format(DAY_OF_WEEK_REGEX, "2021", dayOfWeek, months);
        }
    }

}
