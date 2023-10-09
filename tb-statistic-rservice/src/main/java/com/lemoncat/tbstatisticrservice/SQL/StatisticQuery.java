package com.lemoncat.tbstatisticrservice.SQL;

public class StatisticQuery {
    public static final String getStatisticDayQuery = "SELECT \n" +
            "    most_create_on, most_complete_on\n" +
            "FROM\n" +
            "    (SELECT\n" +
            "         to_char(created_date, 'Day') AS most_create_on\n" +
            "    FROM\n" +
            "        category, tasks\n" +
            "    WHERE\n" +
            "        category.id = tasks.category_id AND category.owner_id = :ownerId\n" +
            "    GROUP BY most_create_on\n" +
            "    ORDER BY COUNT(*) DESC\n" +
            "    LIMIT 1) as ctd,\n" +
            "    (SELECT\n" +
            "         to_char(completed, 'Day') AS most_complete_on\n" +
            "    FROM\n" +
            "        category, tasks\n" +
            "    WHERE\n" +
            "        category.id = tasks.category_id AND category.owner_id = :ownerId\n" +
            "    AND\n" +
            "        tasks.completed IS NOT NULL\n" +
            "    GROUP BY most_complete_on\n" +
            "    ORDER BY COUNT(*) DESC\n" +
            "    LIMIT 1) as ctd1";

    public static final String getStatisticNumberQuery =
            "SELECT created, completed, deleted\n" +
            "FROM\n" +
            "(SELECT\n" +
            "    count(*) as created, status_id\n" +
            "FROM\n" +
            "    category, tasks\n" +
            "WHERE\n" +
            "        category.id = tasks.category_id AND category.owner_id = :ownerId\n" +
            "AND\n" +
            "        tasks.status_id = 1\n" +
            "GROUP BY tasks.status_id) as cscr\n" +
            ",\n" +
            "(SELECT\n" +
            "     count(*) as completed, status_id\n" +
            " FROM\n" +
            "     category, tasks\n" +
            " WHERE\n" +
            "         category.id = tasks.category_id AND category.owner_id = :ownerId\n" +
            "   AND\n" +
            "         tasks.status_id = 2\n" +
            " GROUP BY tasks.status_id) as csco\n" +
            ",\n" +
            "(SELECT\n" +
            "     count(*) as deleted, status_id\n" +
            " FROM\n" +
            "     category, tasks\n" +
            " WHERE\n" +
            "         category.id = tasks.category_id AND category.owner_id = :ownerId\n" +
            "   AND\n" +
            "         tasks.status_id = 3\n" +
            " GROUP BY tasks.status_id) as csd";
}
