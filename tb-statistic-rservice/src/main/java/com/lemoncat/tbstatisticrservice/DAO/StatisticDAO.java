package com.lemoncat.tbstatisticrservice.DAO;

import com.lemoncat.tbstatisticrservice.SQL.StatisticQuery;
import com.lemoncat.tbstatisticrservice.models.StatisticDays;
import com.lemoncat.tbstatisticrservice.models.StatisticNumbers;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public class StatisticDAO {
    private final NamedParameterJdbcTemplate template;
    private final BeanPropertyRowMapper<StatisticDays> rowMapperDays;
    private final BeanPropertyRowMapper<StatisticNumbers> rowMapperNumbers;

    public StatisticDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
        this.rowMapperNumbers = new BeanPropertyRowMapper<>(StatisticNumbers.class);
        this.rowMapperDays = new BeanPropertyRowMapper<>(StatisticDays.class);
    }

    public StatisticDays getStatisticDays(String ownerId) {
        String sql = StatisticQuery.getStatisticDayQuery;
        Map<String, Object> paramMap = Collections.singletonMap("ownerId", ownerId);
        Stream<StatisticDays> s = template.queryForStream(sql, paramMap, rowMapperDays);
        StatisticDays statistic = s.findFirst().orElse(null);
        s.close();

        return statistic;
    }

    public StatisticNumbers getStatisticNumbers(String ownerId) {
        String sql = StatisticQuery.getStatisticNumberQuery;
        Map<String, Object> paramMap = Collections.singletonMap("ownerId", ownerId);
        Stream<StatisticNumbers> s = template.queryForStream(sql, paramMap, rowMapperNumbers);
        StatisticNumbers statistic = s.findFirst().orElse(null);
        s.close();

        return statistic;
    }
}
