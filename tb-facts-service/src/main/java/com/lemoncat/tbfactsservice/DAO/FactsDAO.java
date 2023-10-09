package com.lemoncat.tbfactsservice.DAO;

import com.lemoncat.tbfactsservice.SQL.FactRequest;
import com.lemoncat.tbfactsservice.models.Fact;
import io.micrometer.tracing.SpanName;
import io.micrometer.tracing.annotation.ContinueSpan;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Objects;

@Repository
public class FactsDAO {
    private final JdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Fact> rowMapper;

    public FactsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new BeanPropertyRowMapper<>(Fact.class);
    }

    public Fact getDailyFact() {
        LocalDate today = LocalDate.now();
        int seed = today.getDayOfMonth() + today.getMonthValue() + today.getYear();
        long dayId = (seed * 1024L) % getNumberOfRows();
        dayId = dayId == 0 ? 1 : dayId;

        String sql = FactRequest.getFactByIdQuery;

        return jdbcTemplate.queryForObject(sql, rowMapper, dayId);
    }

    public Long getNumberOfRows() {
        String sql = FactRequest.getNumberOfRows;
        Long result = jdbcTemplate.queryForObject(sql, Long.class);

        return Objects.requireNonNull(result) == 0 ? 1 : result;
    }
}
