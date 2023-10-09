package com.lemoncat.tbstatisticrservice.services;

import com.lemoncat.tbstatisticrservice.DAO.StatisticDAO;
import com.lemoncat.tbstatisticrservice.models.StatisticDays;
import com.lemoncat.tbstatisticrservice.models.StatisticNumbers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticDAO statisticDAO;

    public StatisticDays getStatisticDays(String ownerId) {
        StatisticDays statistic = statisticDAO.getStatisticDays(ownerId);

        return Objects.requireNonNullElseGet(statistic,
                () -> new StatisticDays("", ""));
    };

    public StatisticNumbers getStatisticNumbers(String ownerId) {
        StatisticNumbers statistic = statisticDAO.getStatisticNumbers(ownerId);

        return Objects.requireNonNullElseGet(statistic,
                () -> new StatisticNumbers(0, 0,0));
    };
}
